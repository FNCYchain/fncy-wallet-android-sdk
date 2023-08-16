package com.metaverse.world.wallet.sdk.repository.network.service

import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransaction
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransactionTicketSend
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType2
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType4
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType5
import com.metaverse.world.wallet.sdk.repository.network.response.transaction.TicketResponse
import com.metaverse.world.wallet.sdk.repository.network.response.transaction.TransactionResultResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path

internal interface FncyTransactionAPI {

    @POST("/v2/transfers/estimate")
    suspend fun requestTransactionEstimate(
        @HeaderMap header: Map<String, String>,
        @Body params: ReqTransaction
    ): FncyWalletResponseType4<List<TicketResponse>>

    @GET("/v1/transfers/tickets/{ticketUuid}")
    suspend fun requestTransactionByTicketUuId(
        @HeaderMap header: Map<String, String>,
        @Path("ticketUuid") ticketUuid: String
    ): FncyWalletResponse<List<TicketResponse>>

    @POST("/v2/transfers/tickets")
    suspend fun requestTransferTicketsV2(
        @HeaderMap header: Map<String, String>,
        @Body params: ReqTransaction
    ): FncyWalletResponseType5<Unit>

    @POST("v1/transfers/tickets/{ticketUuid}")
    suspend fun requestTransferTicket(
        @HeaderMap header: Map<String, String>,
        @Path("ticketUuid") ticketUuid: String,
        @Body params: ReqTransactionTicketSend,
    ): FncyWalletResponseType2<TransactionResultResponse>

}