package com.metaverse.world.wallet.sdk.repository.network.service

import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqAnswerValidation
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqMessageSign
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqPasswordChange
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqPasswordChangeWithAnswer
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqPasswordValidation
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqWalletAnswerCreation
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqWalletCreation
import com.metaverse.world.wallet.sdk.model.transaction.FncyTransaction
import com.metaverse.world.wallet.sdk.model.wallet.FncyBalance
import com.metaverse.world.wallet.sdk.model.wallet.FncyGasPrice
import com.metaverse.world.wallet.sdk.model.wallet.FncyMessageSign
import com.metaverse.world.wallet.sdk.model.wallet.FncyQuestion
import com.metaverse.world.wallet.sdk.model.wallet.FncyWallet
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType2
import retrofit2.http.*

internal interface FncyWalletAPI {

    @GET("v1/wallets")
    suspend fun requestWalletList(
        @HeaderMap header: Map<String, String>
    ): FncyWalletResponse<List<FncyWallet>>

    @GET("v1/wallets/{wid}")
    suspend fun requestWalletDetail(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long
    ): FncyWalletResponse<List<FncyWallet>>

    @GET("/v1/wallets/{wid}/balance")
    suspend fun requestTotalWalletBalance(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long
    ): FncyWalletResponse<List<FncyBalance>>

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
    ): FncyWalletResponse<List<FncyQuestion>>

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
    ): FncyWalletResponse<List<FncyQuestion>>

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
    ): FncyWalletResponse<List<FncyGasPrice>>

    @GET("/v1/wallets/{wid}/assets/transfers/recent")
    suspend fun requestRecentTransferHistoryList(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @QueryMap paging: Map<String, String>
    ): FncyWalletResponse<List<FncyTransaction>>

    @GET("/v1/wallets/{wid}/assets/transfers")
    suspend fun requestTransferHistoryList(
        @HeaderMap headerMap: Map<String, String>,
        @Path("wid") wid: Long,
        @QueryMap param: Map<String, String>,
        @QueryMap paging: Map<String, String>
    ): FncyWalletResponse<List<FncyTransaction>>

    @GET("/v2/wallets/{wid}/assets/transfers/{historySeq}")
    suspend fun requestTransferDetailBySeq(
        @HeaderMap headerMap: Map<String, String>,
        @Path("wid") wid: Long,
        @Path("historySeq") transferSeq: Long,
    ): FncyWalletResponse<List<FncyTransaction>>

    @POST("v1/wallets/{wid}/signature")
    suspend fun requestMessageSign(
        @HeaderMap header: Map<String, String>,
        @Path("wid") wid: Long,
        @Body params: ReqMessageSign
    ): FncyWalletResponseType2<FncyMessageSign>

}