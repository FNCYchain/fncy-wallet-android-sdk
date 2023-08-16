package com.metaverse.world.wallet.sdk.repository.data.wallet

import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqAnswerValidation
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqMessageSign
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqPasswordChange
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqPasswordChangeWithAnswer
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqPasswordValidation
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqRecentTransactionHistory
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransferDetail
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransferHistory
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqWalletAnswerCreation
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqWalletCreation
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponse
import com.metaverse.world.wallet.sdk.repository.network.response.FncyWalletResponseType2
import com.metaverse.world.wallet.sdk.repository.network.response.toParamMap
import com.metaverse.world.wallet.sdk.repository.network.response.transaction.TransactionResponse
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.BalanceResponse
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.GasPriceResponse
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.MessageSignResponse
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.QuestionResponse
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.WalletResponse
import com.metaverse.world.wallet.sdk.repository.network.service.FncyWalletAPI
import retrofit2.Retrofit

internal interface FncyWalletDataSource {

    // 지갑 리스트 요청
    suspend fun requestWalletList(
        header: Map<String, String>
    ): FncyWalletResponse<List<WalletResponse>>

    // 지갑 상세 요청
    suspend fun requestWalletDetail(
        header: Map<String, String>,
        wid: Long
    ): FncyWalletResponse<List<WalletResponse>>

    // 지갑 전체 밸런스 요청
    suspend fun requestTotalWalletBalance(
        header: Map<String, String>,
        wid: Long
    ): FncyWalletResponse<List<BalanceResponse>>

    // 패스워드 검증 요청
    suspend fun requestPasswordValidation(
        header: HashMap<String, String>,
        reqPasswordValidation: ReqPasswordValidation
    ): FncyWalletResponse<Unit>

    // 지갑 생성 요청
    suspend fun requestWalletCreation(
        header: HashMap<String, String>,
        reqWalletCreation: ReqWalletCreation
    ): FncyWalletResponse<Unit>

    // 패스워드 변경/마이그레이션 요청
    suspend fun requestPasswordChange(
        header: HashMap<String, String>,
        reqPasswordChange: ReqPasswordChange
    ): FncyWalletResponse<Unit>

    // 선택한 질문 요청
    suspend fun requestChosenWalletQuestion(
        header: HashMap<String, String>
    ): FncyWalletResponse<List<QuestionResponse>>

    // 복원키 유효성 검증 요청
    suspend fun requestWalletAnswerValidation(
        header: HashMap<String, String>,
        reqAnswerValidation: ReqAnswerValidation
    ): FncyWalletResponse<Unit>

    // 복원키로 패스워드 변경 요청
    suspend fun requestWalletPasswordChangeWithAnswer(
        header: HashMap<String, String>,
        reqPasswordChangeWithAnswer: ReqPasswordChangeWithAnswer
    ): FncyWalletResponse<Unit>

    // 복원키 질문 리스트 요청
    suspend fun requestWalletQuestions(
        header: Map<String, String>,
        paging: Map<String, String>
    ): FncyWalletResponse<List<QuestionResponse>>

    // 복원키 생성
    suspend fun requestWalletAddRestoreKeyCreation(
        header: HashMap<String, String>,
        wid: Long,
        reqWalletAnswerCreation: ReqWalletAnswerCreation
    ): FncyWalletResponse<Unit>

    // 가스 수수료 요청
    suspend fun requestSmartGasPrice(
        header: HashMap<String, String>,
        blockchainId: Long
    ): FncyWalletResponse<List<GasPriceResponse>>

    // 자산 전송 히스토리 목록
    suspend fun requestTransferHistoryList(
        header: Map<String, String>,
        reqTransferHistory: ReqTransferHistory
    ): FncyWalletResponse<List<TransactionResponse>>

    // 최근 트랜잭션 히스토리 요청
    suspend fun requestRecentTransferHistoryList(
        header: HashMap<String, String>,
        reqRecentTransactionHistory: ReqRecentTransactionHistory
    ): FncyWalletResponse<List<TransactionResponse>>

    suspend fun requestTransferDetailBySeq(
        header: Map<String, String>,
        reqTransferDetail: ReqTransferDetail
    ): FncyWalletResponse<List<TransactionResponse>>

    suspend fun requestMessageSign(
        header: HashMap<String, String>,
        wid: Long,
        reqMessageSign: ReqMessageSign
    ): FncyWalletResponseType2<MessageSignResponse>

}

internal class FncyWalletDataSourceImpl(
    private val retrofit: Retrofit
): FncyWalletDataSource {

    override suspend fun requestWalletList(header: Map<String, String>): FncyWalletResponse<List<WalletResponse>> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestWalletList(header)
    }

    override suspend fun requestWalletDetail(
        header: Map<String, String>,
        wid: Long
    ): FncyWalletResponse<List<WalletResponse>> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestWalletDetail(header, wid)
    }

    override suspend fun requestTotalWalletBalance(
        header: Map<String, String>,
        wid: Long
    ): FncyWalletResponse<List<BalanceResponse>> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestTotalWalletBalance(header, wid)
    }

    override suspend fun requestPasswordValidation(
        header: HashMap<String, String>,
        reqPasswordValidation: ReqPasswordValidation
    ): FncyWalletResponse<Unit> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestWalletPasswordValidation(
            header,
            reqPasswordValidation
        )
    }

    override suspend fun requestWalletCreation(
        header: HashMap<String, String>,
        reqWalletCreation: ReqWalletCreation
    ): FncyWalletResponse<Unit> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestWalletCreationV2(
            header,
            reqWalletCreation
        )
    }

    override suspend fun requestPasswordChange(
        header: HashMap<String, String>,
        reqPasswordChange: ReqPasswordChange
    ): FncyWalletResponse<Unit> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestWalletPasswordChange(
            header,
            reqPasswordChange
        )
    }

    override suspend fun requestChosenWalletQuestion(
        header: HashMap<String, String>
    ): FncyWalletResponse<List<QuestionResponse>> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestWalletQuestion(header)
    }

    override suspend fun requestWalletAnswerValidation(
        header: HashMap<String, String>,
        reqAnswerValidation: ReqAnswerValidation
    ): FncyWalletResponse<Unit> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestWalletAnswerValidation(
            header,
            reqAnswerValidation
        )
    }

    override suspend fun requestWalletPasswordChangeWithAnswer(
        header: HashMap<String, String>,
        reqPasswordChangeWithAnswer: ReqPasswordChangeWithAnswer
    ): FncyWalletResponse<Unit> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestWalletPasswordChangeWithAnswer(
            header,
            reqPasswordChangeWithAnswer
        )
    }

    override suspend fun requestWalletQuestions(
        header: Map<String, String>,
        paging: Map<String, String>
    ): FncyWalletResponse<List<QuestionResponse>> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestQuestions(
            header,
            paging
        )
    }

    override suspend fun requestWalletAddRestoreKeyCreation(
        header: HashMap<String, String>,
        wid: Long,
        reqWalletAnswerCreation: ReqWalletAnswerCreation
    ): FncyWalletResponse<Unit> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestWalletAddRestoreKeyCreation(
            header,
            wid,
            reqWalletAnswerCreation
        )
    }

    override suspend fun requestSmartGasPrice(
        header: HashMap<String, String>,
        blockchainId: Long
    ): FncyWalletResponse<List<GasPriceResponse>> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestSmartGasPrice(
            header,
            blockchainId
        )
    }


    override suspend fun requestRecentTransferHistoryList(
        header: HashMap<String, String>,
        reqRecentTransactionHistory: ReqRecentTransactionHistory
    ): FncyWalletResponse<List<TransactionResponse>> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestRecentTransferHistoryList(
            header,
            reqRecentTransactionHistory.wid,
            reqRecentTransactionHistory.run { mapOf("limit" to this.limit.toString()) }
        )
    }

    override suspend fun requestTransferHistoryList(
        header: Map<String, String>,
        reqTransferHistory: ReqTransferHistory
    ): FncyWalletResponse<List<TransactionResponse>> {
        val fncyTransaction = retrofit.create(FncyWalletAPI::class.java)
        return fncyTransaction.requestTransferHistoryList(
            header,
            reqTransferHistory.wid,
            reqTransferHistory.toParamMap(),
            reqTransferHistory.paging.toParamMap()
        )
    }

    override suspend fun requestTransferDetailBySeq(
        header: Map<String, String>,
        reqTransferDetail: ReqTransferDetail
    ): FncyWalletResponse<List<TransactionResponse>> {
        val fncyTransaction = retrofit.create(FncyWalletAPI::class.java)
        return fncyTransaction.requestTransferDetailBySeq(
            header,
            reqTransferDetail.wid,
            reqTransferDetail.historySeq
        )
    }

    override suspend fun requestMessageSign(
        header: HashMap<String, String>,
        wid: Long,
        reqMessageSign: ReqMessageSign
    ): FncyWalletResponseType2<MessageSignResponse> {
        val fncyWallet = retrofit.create(FncyWalletAPI::class.java)
        return fncyWallet.requestMessageSign(
            header,
            wid,
            reqMessageSign
        )
    }
}

private fun ReqTransferHistory.toParamMap(): Map<String, String> {
    val paramMap = mutableMapOf(
        "assetId" to assetId.toString(),
    )

    paramMap.run {
        inOut?.let { put("inOut", it) }
    }

    return paramMap.toMap()
}