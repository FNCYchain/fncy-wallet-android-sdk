package com.metaverse.world.wallet.sdk.repository.data.account

import com.metaverse.world.wallet.sdk.repository.network.request.FncyRequest
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqRemoveDeviceToken
import com.metaverse.world.wallet.sdk.repository.network.parser.ApiResultParser
import com.metaverse.world.wallet.sdk.repository.network.response.isSuccess
import com.metaverse.world.wallet.sdk.repository.network.response.successData
import com.metaverse.world.wallet.sdk.utils.toHeader
import com.metaverse.world.wallet.sdk.utils.withContextRun
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber

internal interface FncyAccountRepository {

    suspend fun insertUser(
        request: FncyRequest<Unit>
    ): Result<Boolean>

    // KMS RSA Key 요청
    suspend fun requestRsaKey(
        request: FncyRequest<Unit>
    ): Result<String>

    // UUID 삭제 요청
    suspend fun requestRemoveDeviceToken(
        request: FncyRequest<ReqRemoveDeviceToken>
    ): Result<Unit>
}

internal class FncyAccountRepositoryImpl(
    private val fncyAccountDataSource: FncyAccountDataSource,
    private val apiResultParser: ApiResultParser,
    private val ioDispatcher: CoroutineDispatcher
) : FncyAccountRepository {

    init {
        Timber.w("$this created.")
    }

    override suspend fun insertUser(
        request: FncyRequest<Unit>
    ): Result<Boolean> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyAccountDataSource.insertUser(
                request.accessToken.toHeader()
            )
        )

        result.isSuccess()
    }


    override suspend fun requestRsaKey(
        request: FncyRequest<Unit>
    ): Result<String> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        )

        result.userRsaPubKey
    }


    override suspend fun requestRemoveDeviceToken(
        request: FncyRequest<ReqRemoveDeviceToken>
    ): Result<Unit> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyAccountDataSource.requestRemoveDeviceToken(
                request.accessToken.toHeader(),
                request.params
            )
        )

        result.successData()
    }

}