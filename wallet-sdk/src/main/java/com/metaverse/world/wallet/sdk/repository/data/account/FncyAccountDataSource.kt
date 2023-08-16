package com.metaverse.world.wallet.sdk.repository.data.account

import com.metaverse.world.wallet.sdk.repository.network.response.account.KmsRsaPubKey
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqRemoveDeviceToken
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType2
import com.metaverse.world.wallet.sdk.repository.network.service.FncyAccountAPI
import com.metaverse.world.wallet.sdk.repository.network.service.FncyWalletAPI
import retrofit2.Retrofit
import timber.log.Timber

internal interface FncyAccountDataSource {

    // Fncy 서버 계정 삽입
    suspend fun insertUser(authorizationTokenHeader: HashMap<String, String>): FncyWalletResponse<Unit>

    // Request Rsa Key to KMS
    suspend fun requestRsaKey(
        authorizationTokenHeader: HashMap<String, String>
    ): FncyWalletResponseType2<KmsRsaPubKey>

    // UUID 삭제 요청
    suspend fun requestRemoveDeviceToken(
        header: HashMap<String, String>,
        reqRemoveDeviceToken: ReqRemoveDeviceToken
    ): FncyWalletResponse<Unit>

}

internal class FncyAccountDataSourceImpl(
    private val retrofit: Retrofit,
) : FncyAccountDataSource {
    init {
        Timber.w("$this created.")
    }
    override suspend fun insertUser(authorizationTokenHeader: HashMap<String, String>): FncyWalletResponse<Unit> {
        val fncyAccountAPI = retrofit.create(FncyAccountAPI::class.java)
        return fncyAccountAPI.insertUser(authorizationTokenHeader)
    }

    override suspend fun requestRsaKey(
        authorizationTokenHeader: HashMap<String, String>
    ): FncyWalletResponseType2<KmsRsaPubKey> {
        val fncyAccountAPI = retrofit.create(FncyAccountAPI::class.java)
        return fncyAccountAPI.requestRsaKey(authorizationTokenHeader)
    }

    override suspend fun requestRemoveDeviceToken(
        header: HashMap<String, String>,
        reqRemoveDeviceToken: ReqRemoveDeviceToken
    ): FncyWalletResponse<Unit> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestRemoveDeviceToken(
            header,
            reqRemoveDeviceToken.uuid
        )
    }
}
