package com.metaverse.world.wallet.sdk.repository.network.service

import com.metaverse.world.wallet.sdk.repository.network.response.account.KmsRsaPubKey
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType2
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

internal interface FncyAccountAPI {

    @POST("/v1/users")
    suspend fun insertUser(
        @HeaderMap header: Map<String, String>
    ): FncyWalletResponse<Unit>

    @GET("v1/users/rsa-public")
    suspend fun requestRsaKey(
        @HeaderMap header: Map<String, String>
    ): FncyWalletResponseType2<KmsRsaPubKey>

}