package com.metaverse.world.wallet.sdk.repository.data.transaction

import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransaction
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransactionByTicket
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransactionTicketSend
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType2
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType4
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType5
import com.metaverse.world.wallet.sdk.repository.network.response.transaction.TicketResponse
import com.metaverse.world.wallet.sdk.repository.network.response.transaction.TransactionResultResponse
import com.metaverse.world.wallet.sdk.repository.network.service.FncyTransactionAPI
import retrofit2.Retrofit
import timber.log.Timber

internal interface FncyTransactionDataSource {

    // Ticket id 트랜잭션 정보 요청
    suspend fun requestTransactionByTicketUuId(
        header: HashMap<String, String>,
        reqTransactionByTicket: ReqTransactionByTicket
    ): FncyWalletResponse<List<TicketResponse>>

    //  트랜잭션 Ticket 생성 요청
    suspend fun requestTransactionTicket(
        header: HashMap<String, String>,
        reqTransaction: ReqTransaction
    ): FncyWalletResponseType5<Unit>

    suspend fun requestTransactionTicketSend(
        header: HashMap<String, String>,
        reqTransactionTicketSend: ReqTransactionTicketSend
    ): FncyWalletResponseType2<TransactionResultResponse>

    // 트랜잭션 수수료 예측 요청
    suspend fun requestTransactionEstimate(
        header: HashMap<String, String>,
        reqTransaction: ReqTransaction
    ): FncyWalletResponseType4<List<TicketResponse>>

}

internal class FncyTransactionDataSourceImpl(
    private val retrofit: Retrofit
): FncyTransactionDataSource {

    init {
        Timber.d("$this created.")
    }

    override suspend fun requestTransactionByTicketUuId(
        header: HashMap<String, String>,
        reqTransactionByTicket: ReqTransactionByTicket
    ): FncyWalletResponse<List<TicketResponse>> {
        val fncyTransaction = retrofit.create(FncyTransactionAPI::class.java)
        return fncyTransaction.requestTransactionByTicketUuId(
            header,
            reqTransactionByTicket.ticketUuid
        )
    }

    override suspend fun requestTransactionTicket(
        header: HashMap<String, String>,
        reqTransaction: ReqTransaction
    ): FncyWalletResponseType5<Unit> {
        val fncyTransaction = retrofit.create(FncyTransactionAPI::class.java)
        return fncyTransaction.requestTransferTicketsV2(
            header,
            reqTransaction
        )
    }

    override suspend fun requestTransactionTicketSend(
        header: HashMap<String, String>,
        reqTransactionTicketSend: ReqTransactionTicketSend
    ): FncyWalletResponseType2<TransactionResultResponse> {
        val fncyTransaction = retrofit.create(FncyTransactionAPI::class.java)
        return fncyTransaction.requestTransferTicket(
            header,
            reqTransactionTicketSend.ticketUuid,
            reqTransactionTicketSend
        )
    }

    override suspend fun requestTransactionEstimate(
        header: HashMap<String, String>,
        reqTransaction: ReqTransaction
    ): FncyWalletResponseType4<List<TicketResponse>> {
        val fncyTransaction = retrofit.create(FncyTransactionAPI::class.java)
        return fncyTransaction.requestTransactionEstimate(
            header,
            reqTransaction
        )
    }

}
