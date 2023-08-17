package com.metaverse.world.wallet.sdk.repository.data.asset

import com.metaverse.world.wallet.sdk.model.asset.FncyAsset
import com.metaverse.world.wallet.sdk.model.asset.FncyAssetInfo
import com.metaverse.world.wallet.sdk.model.asset.FncyChainInfo
import com.metaverse.world.wallet.sdk.model.asset.FncyCurrency
import com.metaverse.world.wallet.sdk.model.nft.FncyNFT
import com.metaverse.world.wallet.sdk.repository.network.parser.ApiResultParser
import com.metaverse.world.wallet.sdk.repository.network.request.FncyRequest
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqAssetByAssetId
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqAssetList
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqAssetsByCategory
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqBlockchainAssetByContractAddress
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqBlockchainInfo
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqNftAssetByNftId
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqNftAssetByOption
import com.metaverse.world.wallet.sdk.repository.network.request.internal.asByCategory
import com.metaverse.world.wallet.sdk.repository.network.response.PagingData
import com.metaverse.world.wallet.sdk.repository.network.response.asset.asDomain
import com.metaverse.world.wallet.sdk.repository.network.response.nft.asDomain
import com.metaverse.world.wallet.sdk.utils.toHeader
import com.metaverse.world.wallet.sdk.utils.withContextRun
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber

internal interface FncyAssetRepository {

    suspend fun requestAssetsByCategory(
        request: FncyRequest<ReqAssetsByCategory>
    ): Result<PagingData<List<FncyAsset>?>>

    suspend fun requestAssetList(
        request: FncyRequest<ReqAssetList>
    ): Result<PagingData<List<FncyAsset>?>>

    suspend fun requestAssetByAssetId(
        request: FncyRequest<ReqAssetByAssetId>
    ): Result<FncyAsset?>

    suspend fun requestAssetCurrency(
        request: FncyRequest<Unit>
    ): Result<FncyCurrency?>

    suspend fun requestNftsByOption(
        request: FncyRequest<ReqNftAssetByOption>
    ): Result<PagingData<List<FncyNFT>?>>

    suspend fun requestNftAssetByNftId(
        request: FncyRequest<ReqNftAssetByNftId>
    ): Result<FncyNFT?>

    suspend fun requestBlockchainPlatform(
        request: FncyRequest<ReqBlockchainInfo>
    ): Result<FncyChainInfo?>

    suspend fun requestBlockchainPlatformAssetList(
        request: FncyRequest<ReqBlockchainAssetByContractAddress>
    ): Result<List<FncyAssetInfo>?>
}

internal class FncyAssetRepositoryImpl(
    private val fncyAssetDataSource: FncyAssetDataSource,
    private val apiResultParser: ApiResultParser,
    private val ioDispatcher: CoroutineDispatcher
) : FncyAssetRepository {

    init {
        Timber.d("$this created.")
    }

    override suspend fun requestAssetsByCategory(
        request: FncyRequest<ReqAssetsByCategory>
    ): Result<PagingData<List<FncyAsset>?>> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyAssetDataSource.requestAssetsByCategory(
                request.accessToken.toHeader(),
                request.params
            )
        )

        PagingData(
            result.items?.map { it.asDomain() },
            requireNotNull(result.paging)
        )
    }

    override suspend fun requestAssetList(
        request: FncyRequest<ReqAssetList>
    ): Result<PagingData<List<FncyAsset>?>> =
        withContextRun(ioDispatcher) {
            val result = apiResultParser.parse(
                if (request.params.fncyAssetCategory != null) {
                    fncyAssetDataSource.requestAssetList(
                        request.accessToken.toHeader(),
                        request.params
                    )
                } else {
                    fncyAssetDataSource.requestAssetsByCategory(
                        request.accessToken.toHeader(),
                        request.params.asByCategory()
                    )
                }
            )

            PagingData(
                result.items?.map { it.asDomain() },
                requireNotNull(result.paging)
            )
        }

    override suspend fun requestAssetByAssetId(
        request: FncyRequest<ReqAssetByAssetId>
    ): Result<FncyAsset?> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyAssetDataSource.requestAssetByAssetId(
                request.accessToken.toHeader(),
                request.params
            )
        )

        result.items?.first()?.asDomain()
    }

    override suspend fun requestAssetCurrency(
        request: FncyRequest<Unit>
    ): Result<FncyCurrency?> =
        withContextRun(ioDispatcher) {
            val result = apiResultParser.parse(
                fncyAssetDataSource.requestAssetCurrency(
                    request.accessToken.toHeader(),
                )
            )

            result.items?.first()?.asDomain()

        }

    override suspend fun requestNftsByOption(
        request: FncyRequest<ReqNftAssetByOption>
    ): Result<PagingData<List<FncyNFT>?>> =
        withContextRun(ioDispatcher) {
            val result = apiResultParser.parse(
                fncyAssetDataSource.requestNftsByOption(
                    request.accessToken.toHeader(),
                    request.params
                )
            )

            PagingData(
                result.items?.map { it.asDomain() },
                requireNotNull(result.paging)
            )
        }

    override suspend fun requestNftAssetByNftId(
        request: FncyRequest<ReqNftAssetByNftId>
    ): Result<FncyNFT?> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyAssetDataSource.requestNftByNftId(
                request.accessToken.toHeader(),
                request.params
            )
        )

        result.items?.first()?.asDomain()
    }

    override suspend fun requestBlockchainPlatform(
        request: FncyRequest<ReqBlockchainInfo>
    ): Result<FncyChainInfo?> =
        withContextRun(ioDispatcher) {
            val result = apiResultParser.parse(
                fncyAssetDataSource.requestBlockchainPlatformAsset(
                    request.accessToken.toHeader(),
                    request.params
                )
            )

            result.items?.first()?.asDomain()
                ?: throw IllegalStateException("Asset Id Not Found")
        }

    override suspend fun requestBlockchainPlatformAssetList(
        request: FncyRequest<ReqBlockchainAssetByContractAddress>
    ): Result<List<FncyAssetInfo>?> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyAssetDataSource.requestBlockchainPlatformAssetList(
                request.accessToken.toHeader(),
                request.params
            )
        )

        result.items?.map { it.asDomain() }
    }

}