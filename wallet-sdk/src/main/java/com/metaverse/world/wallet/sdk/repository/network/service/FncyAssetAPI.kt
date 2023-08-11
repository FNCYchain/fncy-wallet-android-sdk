package com.metaverse.world.wallet.sdk.repository.network.service

import com.metaverse.world.wallet.sdk.model.asset.FncyChainInfo
import com.metaverse.world.wallet.sdk.model.nft.FncyNFT
import com.metaverse.world.wallet.sdk.model.asset.FncyCurrency
import com.metaverse.world.wallet.sdk.model.asset.FncyAssetInfo
import com.metaverse.world.wallet.sdk.model.asset.FncyAsset
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.QueryMap

internal interface FncyAssetAPI {

    @GET("/v1/wallets/{wid}/assets-all")
    suspend fun requestAssetsWithCategory(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @QueryMap params: Map<String, String>,
        @QueryMap paging: Map<String, String>
    ): FncyWalletResponse<List<FncyAsset>>

    @GET("/v1/wallets/{wid}/assets-all")
    suspend fun requestAssetList(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @QueryMap paging: Map<String, String>
    ): FncyWalletResponse<List<FncyAsset>>

    @GET("/v1/wallets/{wid}/assets/{assetId}")
    suspend fun requestAssetByAssetId(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @Path("assetId") assetId: Long
    ): FncyWalletResponse<List<FncyAsset>>

    @GET("/v1/block-chains/fncy-info")
    suspend fun requestAssetCurrency(
        @HeaderMap header: Map<String, String>,
    ): FncyWalletResponse<List<FncyCurrency>>

    @GET("/v1/wallets/{wid}/nfts")
    suspend fun requestNftsByWid(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @QueryMap params: Map<String, String>,
        @QueryMap paging: Map<String, String>
    ): FncyWalletResponse<List<FncyNFT>>

    @GET("/v1/wallets/{wid}/nfts/{nftId}")
    suspend fun requestNftByNftId(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @Path("nftId") nftId: Long
    ): FncyWalletResponse<List<FncyNFT>>

    //https:devx-apis.cubeapi.io/v1/block-chains/:chainId/assets
    @GET("/v1/block-chains/{chainId}")
    suspend fun requestBlockchainPlatformAsset(
        @HeaderMap header: Map<String, String>,
        @Path("chainId") chainId: Long
    ): FncyWalletResponse<List<FncyChainInfo>>

    @GET("/v1/block-chains/{chainId}/assets/contractAddress/{contractAddress}")
    suspend fun requestBlockchainPlatformAssetList(
        @HeaderMap header: Map<String, String>,
        @Path("chainId") chainId: Long,
        @Path("contractAddress") contractAddress: String
    ): FncyWalletResponse<List<FncyAssetInfo>>

}