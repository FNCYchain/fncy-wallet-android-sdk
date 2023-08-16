package com.metaverse.world.wallet.sdk.repository.network.service

import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqAnswerValidation
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqMessageSign
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqPasswordChange
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqPasswordChangeWithAnswer
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqPasswordValidation
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqWalletAnswerCreation
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqWalletCreation
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType2
import com.metaverse.world.wallet.sdk.repository.network.response.transaction.TransactionResponse
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.BalanceResponse
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.GasPriceResponse
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.MessageSignResponse
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.QuestionResponse
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.WalletResponse
import retrofit2.http.*

internal interface FncyWalletAPI {

    @GET("v1/wallets")
    suspend fun requestWalletList(
        @HeaderMap header: Map<String, String>
    ): FncyWalletResponse<List<WalletResponse>>

    @GET("v1/wallets/{wid}")
    suspend fun requestWalletDetail(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long
    ): FncyWalletResponse<List<WalletResponse>>

    @GET("/v1/wallets/{wid}/balance")
    suspend fun requestTotalWalletBalance(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long
    ): FncyWalletResponse<List<BalanceResponse>>

    @POST("/v1/wallets/pin-check")
    suspend fun requestWalletPasswordValidation(
        @HeaderMap header: Map<String, String>,
        @Body reqPasswordValidation: ReqPasswordValidation
    ): FncyWalletResponse<Unit>

    @POST("v2/wallets")
    suspend fun requestWalletCreationV2(
        @HeaderMap header: Map<String, String>,
        @Body reqWalletCreation: ReqWalletCreation
    ): FncyWalletResponse<Unit>

    @PATCH("/v1/wallets/pin")
    suspend fun requestWalletPasswordChange(
        @HeaderMap header: Map<String, String>,
        @Body reqPasswordChange: ReqPasswordChange
    ): FncyWalletResponse<Unit>

    @GET("/v1/wallets/restore/questions")
    suspend fun requestWalletQuestion(
        @HeaderMap header: Map<String, String>
    ): FncyWalletResponse<List<QuestionResponse>>

    @POST("/v1/wallets/answer-check")
    suspend fun requestWalletAnswerValidation(
        @HeaderMap header: Map<String, String>,
        @Body reqAnswerValidation: ReqAnswerValidation
    ): FncyWalletResponse<Unit>

    @PATCH("/v1/wallets/restore/answer")
    suspend fun requestWalletPasswordChangeWithAnswer(
        @HeaderMap header: Map<String, String>,
        @Body params: ReqPasswordChangeWithAnswer
    ): FncyWalletResponse<Unit>

    @GET("v1/questions")
    suspend fun requestQuestions(
        @HeaderMap header: Map<String, String>,
        @QueryMap paging: Map<String, String>
    ): FncyWalletResponse<List<QuestionResponse>>

    @POST("v2/wallets/{wid}/restore")
    suspend fun requestWalletAddRestoreKeyCreation(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @Body params: ReqWalletAnswerCreation
    ): FncyWalletResponse<Unit>

    @DELETE("/v1/users/user-devices/{uuid}/token")
    suspend fun requestRemoveDeviceToken(
        @HeaderMap header: Map<String, String>,
        @Path("uuid") params: String
    ): FncyWalletResponse<Unit>

    @GET("v1/block-chains/{chainId}/gas-price")
    suspend fun requestSmartGasPrice(
        @HeaderMap header: Map<String, String>,
        @Path("chainId") chainId: Long
    ): FncyWalletResponse<List<GasPriceResponse>>

    @GET("/v1/wallets/{wid}/assets/transfers/recent")
    suspend fun requestRecentTransferHistoryList(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @QueryMap paging: Map<String, String>
    ): FncyWalletResponse<List<TransactionResponse>>

    @GET("/v1/wallets/{wid}/assets/transfers")
    suspend fun requestTransferHistoryList(
        @HeaderMap headerMap: Map<String, String>,
        @Path("wid") wid: Long,
        @QueryMap param: Map<String, String>,
        @QueryMap paging: Map<String, String>
    ): FncyWalletResponse<List<TransactionResponse>>

    @GET("/v2/wallets/{wid}/assets/transfers/{historySeq}")
    suspend fun requestTransferDetailBySeq(
        @HeaderMap headerMap: Map<String, String>,
        @Path("wid") wid: Long,
        @Path("historySeq") transferSeq: Long,
    ): FncyWalletResponse<List<TransactionResponse>>

    @POST("v1/wallets/{wid}/signature")
    suspend fun requestMessageSign(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @Body params: ReqMessageSign
    ): FncyWalletResponseType2<MessageSignResponse>

}