package com.metaverse.world.wallet.sdk.repository.data.asset

import com.metaverse.world.wallet.sdk.model.asset.FncyAsset
import com.metaverse.world.wallet.sdk.model.asset.FncyAssetInfo
import com.metaverse.world.wallet.sdk.model.asset.FncyChainInfo
import com.metaverse.world.wallet.sdk.model.asset.FncyCurrency
import com.metaverse.world.wallet.sdk.model.nft.FncyNFT
import com.metaverse.world.wallet.sdk.model.request.FncyRequest
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqAssetByAssetId
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqAssetList
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqAssetsByCategory
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqBlockchainAssetByContractAddress
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqBlockchainInfo
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqNftAssetByNftId
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqNftAssetByOption
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.asByCategory
import com.metaverse.world.wallet.sdk.repository.network.parser.ApiResultParser
import com.metaverse.world.wallet.sdk.repository.network.response.PagingData
import com.metaverse.world.wallet.sdk.repository.network.response.toPagingResponse
import com.metaverse.world.wallet.sdk.utils.toHeader
import com.metaverse.world.wallet.sdk.utils.withContextRun
import kotlinx.coroutines.CoroutineDispatcher

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

    override suspend fun requestAssetsByCategory(
        request: FncyRequest<ReqAssetsByCategory>
    ): Result<PagingData<List<FncyAsset>?>> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyAssetDataSource.requestAssetsByCategory(
                request.accessToken.toHeader(),
                request.params
            )
        )

        result.toPagingResponse()
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

            result.toPagingResponse()
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

        result.items?.first()
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

            result.items?.first()

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

            result.toPagingResponse()
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

        result.items?.first()
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

            result.items?.first()
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

        result.items
    }

}