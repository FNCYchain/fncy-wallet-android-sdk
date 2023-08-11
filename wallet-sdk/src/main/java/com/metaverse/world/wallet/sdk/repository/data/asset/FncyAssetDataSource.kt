package com.metaverse.world.wallet.sdk.repository.data.asset

import com.metaverse.world.wallet.sdk.model.asset.FncyAsset
import com.metaverse.world.wallet.sdk.model.asset.FncyCurrency
import com.metaverse.world.wallet.sdk.model.asset.FncyAssetInfo
import com.metaverse.world.wallet.sdk.model.asset.FncyChainInfo
import com.metaverse.world.wallet.sdk.model.nft.FncyNFT
import com.metaverse.world.wallet.sdk.model.etc.NFTOption
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqAssetByAssetId
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqAssetList
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqAssetsByCategory
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqBlockchainInfo
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqBlockchainAssetByContractAddress
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqNftAssetByNftId
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqNftAssetByOption
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import com.metaverse.world.wallet.sdk.repository.network.response.toParamMap
import com.metaverse.world.wallet.sdk.repository.network.service.FncyAssetAPI
import retrofit2.Retrofit
import timber.log.Timber

internal interface FncyAssetDataSource {

    // 카테고리 별 자산 요청
    suspend fun requestAssetsByCategory(
        header: Map<String, String>,
        reqAssetsByCategory: ReqAssetsByCategory
    ): FncyWalletResponse<List<FncyAsset>>

    suspend fun requestAssetList(
        header: Map<String, String>,
        reqAssetList: ReqAssetList
    ): FncyWalletResponse<List<FncyAsset>>

    // 자산 단건 조회
    suspend fun requestAssetByAssetId(
        header: Map<String, String>,
        reqAssetByAssetId: ReqAssetByAssetId
    ): FncyWalletResponse<List<FncyAsset>>

    // 자산 환율 정보 요청
    suspend fun requestAssetCurrency(
        header: Map<String, String>,
    ): FncyWalletResponse<List<FncyCurrency>>

    suspend fun requestNftsByOption(
        header: Map<String, String>,
        reqNftAssetByOption: ReqNftAssetByOption
    ): FncyWalletResponse<List<FncyNFT>>

    // Nft 단건 조회
    suspend fun requestNftByNftId(
        header: Map<String, String>,
        reqNftAssetByNftId: ReqNftAssetByNftId
    ): FncyWalletResponse<List<FncyNFT>>

    suspend fun requestBlockchainPlatformAsset(
        header: Map<String, String>,
        reqBlockchainInfo: ReqBlockchainInfo
    ): FncyWalletResponse<List<FncyChainInfo>>

    suspend fun requestBlockchainPlatformAssetList(
        header: Map<String, String>,
        reqBlockchainAssetByContractAddress: ReqBlockchainAssetByContractAddress
    ): FncyWalletResponse<List<FncyAssetInfo>>
}

internal class FncyAssetDataSourceImpl(
    private val retrofit: Retrofit
) : FncyAssetDataSource {

    init {
        Timber.d("$this created.")
    }

    override suspend fun requestAssetsByCategory(
        header: Map<String, String>,
        reqAssetsByCategory: ReqAssetsByCategory
    ): FncyWalletResponse<List<FncyAsset>> {
        val fncyAsset = retrofit.create(FncyAssetAPI::class.java)
        return fncyAsset.requestAssetsWithCategory(
            header,
            reqAssetsByCategory.wid,
            reqAssetsByCategory.fncyAssetCategory.toMap(),
            reqAssetsByCategory.paging.toParamMap()
        )
    }

    override suspend fun requestAssetList(
        header: Map<String, String>,
        reqAssetList: ReqAssetList
    ): FncyWalletResponse<List<FncyAsset>> {
        val fncyAsset = retrofit.create(FncyAssetAPI::class.java)
        return fncyAsset.requestAssetList(
            header,
            reqAssetList.wid,
            reqAssetList.paging.toParamMap()
        )
    }

    override suspend fun requestAssetByAssetId(
        header: Map<String, String>,
        reqAssetByAssetId: ReqAssetByAssetId
    ): FncyWalletResponse<List<FncyAsset>> {
        val fncyAsset = retrofit.create(FncyAssetAPI::class.java)
        return fncyAsset.requestAssetByAssetId(
            header,
            reqAssetByAssetId.wid,
            reqAssetByAssetId.assetId
        )
    }

    override suspend fun requestAssetCurrency(
        header: Map<String, String>
    ): FncyWalletResponse<List<FncyCurrency>> {
        val fncyAsset = retrofit.create(FncyAssetAPI::class.java)
        return fncyAsset.requestAssetCurrency(
            header
        )
    }

    override suspend fun requestNftsByOption(
        header: Map<String, String>,
        reqNftAssetByOption: ReqNftAssetByOption
    ): FncyWalletResponse<List<FncyNFT>> {
        val fncyAsset = retrofit.create(FncyAssetAPI::class.java)
        return fncyAsset.requestNftsByWid(
            header,
            reqNftAssetByOption.wid,
            reqNftAssetByOption.toParamMap(),
            reqNftAssetByOption.paging.toParamMap()
        )
    }

    override suspend fun requestNftByNftId(
        header: Map<String, String>,
        reqNftAssetByNftId: ReqNftAssetByNftId
    ): FncyWalletResponse<List<FncyNFT>> {
        val fncyAsset = retrofit.create(FncyAssetAPI::class.java)
        return fncyAsset.requestNftByNftId(
            header,
            reqNftAssetByNftId.wid,
            reqNftAssetByNftId.nftId
        )
    }

    override suspend fun requestBlockchainPlatformAsset(
        header: Map<String, String>,
        reqBlockchainInfo: ReqBlockchainInfo
    ): FncyWalletResponse<List<FncyChainInfo>> {
        val fncyAsset = retrofit.create(FncyAssetAPI::class.java)
        return fncyAsset.requestBlockchainPlatformAsset(
            header,
            reqBlockchainInfo.chainId
        )
    }

    override suspend fun requestBlockchainPlatformAssetList(
        header: Map<String, String>,
        reqBlockchainAssetByContractAddress: ReqBlockchainAssetByContractAddress
    ): FncyWalletResponse<List<FncyAssetInfo>> {
        val fncyAsset = retrofit.create(FncyAssetAPI::class.java)
        return fncyAsset.requestBlockchainPlatformAssetList(
            header,
            reqBlockchainAssetByContractAddress.chainId,
            reqBlockchainAssetByContractAddress.contractAddress
        )
    }

}

enum class FncyAssetCategory(
    val fncy: Boolean,
    val cube: Boolean,
    val gameCoin: Boolean,
    val general: Boolean
) {
    FNCY(true, false, false, true),
    FNCY_ASSET(true, false, true, true),
    CUBE(false, true, false, true),
    CUBE_ASSET(false, true, true, true),
    GENERAL(false, false, false, true),
    GENERAL_ETC(false, false, false, false);
}

private fun FncyAssetCategory.toMap() =
    mapOf(
        "fncyYn" to fncy.toParam(),
        "cubeYn" to cube.toParam(),
        "gcoinYn" to gameCoin.toParam(),
        "defaultYn" to general.toParam()
    )

private fun Boolean.toParam() = if (this) "Y" else "N"

private fun ReqNftAssetByOption.toParamMap(): Map<String, String> {
    val list = mutableListOf<Pair<String, String>>()
    option?.let {
        when (it) {
            NFTOption.ForSale, NFTOption.InStock -> list.add("ownerOf" to it.value)
            else -> {}
        }
    }

    return mapOf(*list.toTypedArray())
}

