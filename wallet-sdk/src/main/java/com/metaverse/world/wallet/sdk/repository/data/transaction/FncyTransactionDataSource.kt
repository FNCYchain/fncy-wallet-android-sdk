package com.metaverse.world.wallet.sdk.repository.data.transaction

import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransaction
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransactionByTicket
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransactionTicketSend
import com.metaverse.world.wallet.sdk.model.transaction.FncyTransactionResult
import com.metaverse.world.wallet.sdk.model.transaction.FncyTicket
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType2
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType4
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType5
import com.metaverse.world.wallet.sdk.repository.network.service.FncyTransactionAPI
import retrofit2.Retrofit

internal interface FncyTransactionDataSource {

    // Ticket id 트랜잭션 정보 요청
    suspend fun requestTransactionByTicketUuId(
        header: HashMap<String, String>,
        reqTransactionByTicket: ReqTransactionByTicket
    ): FncyWalletResponse<List<FncyTicket>>

    //  트랜잭션 Ticket 생성 요청
    suspend fun requestTransactionTicket(
        header: HashMap<String, String>,
        reqTransaction: ReqTransaction
    ): FncyWalletResponseType5<Unit>

    suspend fun requestTransactionTicketSend(
        header: HashMap<String, String>,
        reqTransactionTicketSend: ReqTransactionTicketSend
    ): FncyWalletResponseType2<FncyTransactionResult>

    // 트랜잭션 수수료 예측 요청
    suspend fun requestTransactionEstimate(
        header: HashMap<String, String>,
        reqTransaction: ReqTransaction
    ): FncyWalletResponseType4<List<FncyTicket>>

}

internal class FncyTransactionDataSourceImpl(
    private val retrofit: Retrofit
): FncyTransactionDataSource {

    override suspend fun requestTransactionByTicketUuId(
        header: HashMap<String, String>,
        reqTransactionByTicket: ReqTransactionByTicket
    ): FncyWalletResponse<List<FncyTicket>> {
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
    ): FncyWalletResponseType2<FncyTransactionResult> {
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
    ): FncyWalletResponseType4<List<FncyTicket>> {
        val fncyTransaction = retrofit.create(FncyTransactionAPI::class.java)
        fncyTransaction.requestTransactionEstimate(
            header,
            reqTransaction).data
        return fncyTransaction.requestTransactionEstimate(
            header,
            reqTransaction
        )
    }

}
