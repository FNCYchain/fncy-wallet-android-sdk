package com.metaverse.world.wallet.sdk.repository.data.transaction

import com.metaverse.world.wallet.sdk.crypto.FncyEncryptManager
import com.metaverse.world.wallet.sdk.model.error.FncyException
import com.metaverse.world.wallet.sdk.model.request.FncyRequest
import com.metaverse.world.wallet.sdk.model.request.internal.ReqSendTransaction
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransaction
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransactionByTicket
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransactionTicketSend
import com.metaverse.world.wallet.sdk.model.transaction.FncyTicket
import com.metaverse.world.wallet.sdk.model.transaction.FncyTransactionTicket
import com.metaverse.world.wallet.sdk.model.transaction.FncyTxId
import com.metaverse.world.wallet.sdk.repository.data.account.FncyAccountDataSource
import com.metaverse.world.wallet.sdk.repository.network.parser.ApiResultParser
import com.metaverse.world.wallet.sdk.utils.PinValidator
import com.metaverse.world.wallet.sdk.utils.toHeader
import com.metaverse.world.wallet.sdk.utils.withContextRun
import kotlinx.coroutines.CoroutineDispatcher

internal interface FncyTransactionRepository {

    suspend fun requestTransactionByTicket(
        request: FncyRequest<ReqTransactionByTicket>
    ): Result<FncyTicket>

    suspend fun requestTransactionTicket(
        request: FncyRequest<ReqTransaction>
    ): Result<FncyTransactionTicket>

    suspend fun requestTransactionTicketSend(
        request: FncyRequest<ReqSendTransaction>
    ): Result<FncyTxId>

    // 예상 전송 트랜젝션 정보 요청
    suspend fun requestTransactionEstimateInfo(
        request: FncyRequest<ReqTransaction>
    ): Result<FncyTicket>

}

internal class FncyTransactionRepositoryImpl(
    private val fncyTransactionDataSource: FncyTransactionDataSource,
    private val fncyAccountDataSource: FncyAccountDataSource,
    private val apiResultParser: ApiResultParser,
    private val fncyEncryptManager: FncyEncryptManager,
    private val pinValidator: PinValidator,
    private val ioDispatcher: CoroutineDispatcher,
) : FncyTransactionRepository {

    override suspend fun requestTransactionByTicket(
        request: FncyRequest<ReqTransactionByTicket>
    ): Result<FncyTicket> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyTransactionDataSource.requestTransactionByTicketUuId(
                request.accessToken.toHeader(),
                request.params
            )
        )

        result.items?.first() ?: throw throw IllegalStateException("Ticket Error")

    }

    override suspend fun requestTransactionTicket(
        request: FncyRequest<ReqTransaction>
    ): Result<FncyTransactionTicket> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyTransactionDataSource.requestTransactionTicket(
                request.accessToken.toHeader(),
                request.params
            )
        )

        FncyTransactionTicket(
            result.ticketUuid
                ?: throw IllegalStateException("Ticket is Null"),
            result.ticketHash ?: ""
        )

    }

    override suspend fun requestTransactionTicketSend(
        request: FncyRequest<ReqSendTransaction>
    ): Result<FncyTxId> = withContextRun(ioDispatcher) {
        check(pinValidator.valid(request.params.pinNumber)) {
            "Invalid pin number."
        }
        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptData = fncyEncryptManager.encrypt(
            plainData = request.params.pinNumber,
            rsaKey = rsaKey,
            count = 1
        )

        val result = apiResultParser.parse(
            fncyTransactionDataSource.requestTransactionTicketSend(
                request.accessToken.toHeader(),
                ReqTransactionTicketSend(
                    request.params.ticketUUID,
                    encryptData
                )
            )
        )

        FncyTxId(result.txId ?: throw IllegalStateException("TxHash is Null"))

    }

    override suspend fun requestTransactionEstimateInfo(
        request: FncyRequest<ReqTransaction>
    ): Result<FncyTicket> = withContextRun(ioDispatcher) {
        val response = fncyTransactionDataSource.requestTransactionEstimate(
            request.accessToken.toHeader(),
            request.params
        )

        if (response.data?.result?.isSuccess == true) {
            response.data.items?.first()
                ?: throw IllegalStateException("Gas Price is null")
        } else {
            throw FncyException(
                code = response.data?.result?.number ?: 301,
                errMsg = response.data?.web3Error?.message ?: ""
            )
        }

    }
}