package com.metaverse.world.wallet.sdk.repository.network.service

import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import com.metaverse.world.wallet.sdk.repository.network.response.asset.AssetInfoResponse
import com.metaverse.world.wallet.sdk.repository.network.response.asset.AssetResponse
import com.metaverse.world.wallet.sdk.repository.network.response.asset.ChainInfoResponse
import com.metaverse.world.wallet.sdk.repository.network.response.asset.CurrencyResponse
import com.metaverse.world.wallet.sdk.repository.network.response.nft.NFTResponse
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
    ): FncyWalletResponse<List<AssetResponse>>

    @GET("/v1/wallets/{wid}/assets-all")
    suspend fun requestAssetList(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @QueryMap paging: Map<String, String>
    ): FncyWalletResponse<List<AssetResponse>>

    @GET("/v1/wallets/{wid}/assets/{assetId}")
    suspend fun requestAssetByAssetId(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @Path("assetId") assetId: Long
    ): FncyWalletResponse<List<AssetResponse>>

    @GET("/v1/block-chains/fncy-info")
    suspend fun requestAssetCurrency(
        @HeaderMap header: Map<String, String>,
    ): FncyWalletResponse<List<CurrencyResponse>>

    @GET("/v1/wallets/{wid}/nfts")
    suspend fun requestNftsByWid(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @QueryMap params: Map<String, String>,
        @QueryMap paging: Map<String, String>
    ): FncyWalletResponse<List<NFTResponse>>

    @GET("/v1/wallets/{wid}/nfts/{nftId}")
    suspend fun requestNftByNftId(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @Path("nftId") nftId: Long
    ): FncyWalletResponse<List<NFTResponse>>

    //https:devx-apis.cubeapi.io/v1/block-chains/:chainId/assets
    @GET("/v1/block-chains/{chainId}")
    suspend fun requestBlockchainPlatformAsset(
        @HeaderMap header: Map<String, String>,
        @Path("chainId") chainId: Long
    ): FncyWalletResponse<List<ChainInfoResponse>>

    @GET("/v1/block-chains/{chainId}/assets/contractAddress/{contractAddress}")
    suspend fun requestBlockchainPlatformAssetList(
        @HeaderMap header: Map<String, String>,
        @Path("chainId") chainId: Long,
        @Path("contractAddress") contractAddress: String
    ): FncyWalletResponse<List<AssetInfoResponse>>

}