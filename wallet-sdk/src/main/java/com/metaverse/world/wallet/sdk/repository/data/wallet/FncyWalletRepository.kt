package com.metaverse.world.wallet.sdk.repository.data.wallet

import com.metaverse.world.wallet.sdk.crypto.EncryptDataManager
import com.metaverse.world.wallet.sdk.model.request.FncyRequest
import com.metaverse.world.wallet.sdk.model.request.internal.ReqCheckResetAnswer
import com.metaverse.world.wallet.sdk.model.request.internal.ReqCheckWalletPin
import com.metaverse.world.wallet.sdk.model.request.internal.ReqMakeWallet
import com.metaverse.world.wallet.sdk.model.request.internal.ReqPostWalletSign
import com.metaverse.world.wallet.sdk.model.request.internal.ReqRegisterRestorationKey
import com.metaverse.world.wallet.sdk.model.request.internal.ReqResetWalletPin
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqAnswerValidation
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqMessageSign
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqPasswordChange
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqPasswordChangeWithAnswer
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqPasswordValidation
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqRecentTransactionHistory
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqSmartGasPrice
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransferDetail
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransferHistory
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqWalletAnswerCreation
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqWalletCreation
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqWalletId
import com.metaverse.world.wallet.sdk.model.transaction.TransferHistory
import com.metaverse.world.wallet.sdk.model.wallet.FncyQuestion
import com.metaverse.world.wallet.sdk.model.wallet.SmartGasPrice
import com.metaverse.world.wallet.sdk.model.wallet.FncyBalance
import com.metaverse.world.wallet.sdk.model.wallet.FncyWallet
import com.metaverse.world.wallet.sdk.repository.data.account.FncyAccountDataSource
import com.metaverse.world.wallet.sdk.repository.network.parser.ApiResultParser
import com.metaverse.world.wallet.sdk.repository.network.response.Paging
import com.metaverse.world.wallet.sdk.repository.network.response.ResponseWithPaging
import com.metaverse.world.wallet.sdk.repository.network.response.toParamMap
import com.metaverse.world.wallet.sdk.utils.toHeader
import com.metaverse.world.wallet.sdk.utils.withContextRun
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.component.KoinComponent
import timber.log.Timber

internal interface FncyWalletRepository {

    // 지갑 정보 요청
    suspend fun requestWallet(
        request: FncyRequest<Unit>
    ): Result<FncyWallet?>

    // 지갑 단건 요청
    suspend fun requestWalletDetail(
        request: FncyRequest<ReqWalletId>
    ): Result<FncyWallet?>

    // 지갑 전체 밸런스 요청
    suspend fun requestTotalWalletBalance(
        request: FncyRequest<ReqWalletId>
    ): Result<FncyBalance>

    // 패스워드 검증 요청
    suspend fun requestPasswordValidation(
        request: FncyRequest<ReqCheckWalletPin>
    ): Result<Unit>

    // V2 지갑 생성 요청
    suspend fun requestWalletCreation(
        request: FncyRequest<ReqMakeWallet>
    ): Result<Unit>

    // 지갑 비밀번호 변경 및 핀번호 마이그레이션
    suspend fun requestWalletPasswordChange(
        request: FncyRequest<ReqResetWalletPin>
    ): Result<Unit>

    // 선택한 질문 요청
    suspend fun requestChosenWalletQuestion(
        request: FncyRequest<Unit>
    ): Result<FncyQuestion>

    // 지갑 질문 답변 검증 요청
    suspend fun requestWalletAnswerValidation(
        request: FncyRequest<ReqCheckResetAnswer>
    ): Result<Unit>

    // 지갑 질문 답변 패스워드 변경
    suspend fun requestWalletPasswordChangeWithAnswer(
        request: FncyRequest<ReqPasswordChangeWithAnswer>
    ): Result<Unit>

    // 복원키 질문 리스트 요청
    suspend fun requestWalletQuestions(
        request: FncyRequest<Paging>
    ): Result<ResponseWithPaging<List<FncyQuestion>>>

    // 복원키 생성
    suspend fun requestWalletAnswerCreation(
        request: FncyRequest<ReqRegisterRestorationKey>
    ): Result<Unit>

    // 가스 수수료 요청
    suspend fun requestSmartGasPrice(
        request: FncyRequest<ReqSmartGasPrice>
    ): Result<SmartGasPrice>

    suspend fun requestRecentTransferHistoryList(
        request: FncyRequest<ReqRecentTransactionHistory>
    ): Result<List<TransferHistory>?>

    suspend fun requestTransferHistoryList(
        request: FncyRequest<ReqTransferHistory>
    ): Result<ResponseWithPaging<List<TransferHistory>?>>

    suspend fun requestTransferDetailBySeq(
        request: FncyRequest<ReqTransferDetail>
    ): Result<TransferHistory?>

    suspend fun requestMessageSign(
        request: FncyRequest<ReqPostWalletSign>
    ): Result<String>
}

internal class FncyWalletRepositoryImpl(
    private val fncyWalletDataSource: FncyWalletDataSource,
    private val fncyAccountDataSource: FncyAccountDataSource,
    private val apiResultParser: ApiResultParser,
    private val encryptDataManager: EncryptDataManager,
    private val ioDispatcher: CoroutineDispatcher,
) : FncyWalletRepository, KoinComponent {
    init {
        Timber.w("$this created.")
    }
    override suspend fun requestWallet(
        request: FncyRequest<Unit>
    ): Result<FncyWallet?> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestWalletList(
                request.accessToken.toHeader()
            )
        )

        result.items?.getOrNull(0)
    }

    override suspend fun requestWalletDetail(
        request: FncyRequest<ReqWalletId>
    ): Result<FncyWallet?> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestWalletDetail(
                request.accessToken.toHeader(),
                request.params.wid
            )
        )

        result.items?.getOrNull(0)
    }
    override suspend fun requestTotalWalletBalance(
        request: FncyRequest<ReqWalletId>
    ): Result<FncyBalance> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestTotalWalletBalance(
                request.accessToken.toHeader(),
                request.params.wid
            )
        )

        requireNotNull(result.items?.first())
    }
    override suspend fun requestPasswordValidation(
        request: FncyRequest<ReqCheckWalletPin>
    ): Result<Unit> = withContextRun(ioDispatcher) {
        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptPin = encryptDataManager.encrypt(
            plainData = request.params.pinNumber,
            rsaKey = rsaKey,
            count = 2
        )

        apiResultParser.parse(
            fncyWalletDataSource.requestPasswordValidation(
                request.accessToken.toHeader(),
                ReqPasswordValidation(
                    rsaEncryptedHashedPin = encryptPin
                )
            )
        )
    }

    override suspend fun requestWalletCreation(
        request: FncyRequest<ReqMakeWallet>
    ): Result<Unit> = withContextRun(ioDispatcher) {
        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptPin = encryptDataManager.encrypt(
            plainData = request.params.pinNumber,
            rsaKey = rsaKey,
            count = 1
        )

        apiResultParser.parse(
            fncyWalletDataSource.requestWalletCreation(
                request.accessToken.toHeader(),
                ReqWalletCreation(
                    chainId = request.params.chainId,
                    walletNm = request.params.walletName,
                    rsaEncryptUserPin = encryptPin
                )
            )
        )
    }

    override suspend fun requestWalletPasswordChange(
        request: FncyRequest<ReqResetWalletPin>
    ): Result<Unit> = withContextRun(ioDispatcher) {
        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptPin = encryptDataManager.encrypt(
            plainData = request.params.oldPinNumber,
            rsaKey = rsaKey,
            count = 1
        )

        val encryptNewPin = encryptDataManager.encrypt(
            plainData = request.params.newPinNumber,
            rsaKey = rsaKey,
            count = 1
        )

        apiResultParser.parse(
            fncyWalletDataSource.requestPasswordChange(
                request.accessToken.toHeader(),
                ReqPasswordChange(
                    rsaEncryptUserPin = encryptPin,
                    rsaEncryptChangeUserPin = encryptNewPin
                )
            )
        )
    }

    override suspend fun requestChosenWalletQuestion(
        request: FncyRequest<Unit>
    ): Result<FncyQuestion> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestChosenWalletQuestion(
                request.accessToken.toHeader()
            )
        )

        result.items?.first()
            ?: throw IllegalStateException("Question is null")
    }

    override suspend fun requestWalletAnswerValidation(
        request: FncyRequest<ReqCheckResetAnswer>
    ): Result<Unit> = withContextRun(ioDispatcher) {
        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptAnswer = encryptDataManager.encrypt(
            plainData = request.params.answer.lowercase(),
            rsaKey = rsaKey,
            count = 2
        )

        val encryptAnswerAlter = encryptDataManager.encrypt(
            plainData = request.params.answer,
            rsaKey = rsaKey,
            count = 2
        )

        apiResultParser.parse(
            fncyWalletDataSource.requestWalletAnswerValidation(
                request.accessToken.toHeader(),
                ReqAnswerValidation(
                    rsaEncryptedHashedAnswer = encryptAnswer,
                    rsaEncryptedHashedAnswerAlter = encryptAnswerAlter
                )
            )
        )
    }

    override suspend fun requestWalletPasswordChangeWithAnswer(
        request: FncyRequest<ReqPasswordChangeWithAnswer>
    ): Result<Unit> = withContextRun(ioDispatcher) {
        apiResultParser.parse(
            fncyWalletDataSource.requestWalletPasswordChangeWithAnswer(
                request.accessToken.toHeader(),
                request.params
            )
        )
    }

    override suspend fun requestWalletQuestions(
        request: FncyRequest<Paging>
    ): Result<ResponseWithPaging<List<FncyQuestion>>> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestWalletQuestions(
                request.accessToken.toHeader(),
                request.params.toParamMap()
            )
        )

        ResponseWithPaging(
            result.items
                ?: throw IllegalStateException("Question is null"),
            requireNotNull(result.paging)
        )
    }

    override suspend fun requestWalletAnswerCreation(
        request: FncyRequest<ReqRegisterRestorationKey>
    ): Result<Unit> = withContextRun(ioDispatcher) {
        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptQuestion = encryptDataManager.encrypt(
            plainData = request.params.userQuestionSeq.toString(),
            rsaKey = rsaKey,
            count = 1
        )

        val encryptAnswer = encryptDataManager.encrypt(
            plainData = request.params.userAnswer,
            rsaKey = rsaKey,
            count = 1
        )

        val encryptPin = encryptDataManager.encrypt(
            plainData = request.params.pinNumber,
            rsaKey = rsaKey,
            count = 1
        )

        apiResultParser.parse(
            fncyWalletDataSource.requestWalletAddRestoreKeyCreation(
                request.accessToken.toHeader(),
                request.params.wid,
                ReqWalletAnswerCreation(
                    rsaEncryptUserQuestion = encryptQuestion,
                    rsaEncryptUserAnswer = encryptAnswer,
                    rsaEncryptUserPin = encryptPin
                )
            )
        )
    }

    override suspend fun requestSmartGasPrice(
        request: FncyRequest<ReqSmartGasPrice>
    ): Result<SmartGasPrice> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestSmartGasPrice(
                request.accessToken.toHeader(),
                request.params.blockchainId
            )
        )

        result.items?.first()
            ?: throw IllegalStateException("Gas Price is null")
    }

    override suspend fun requestRecentTransferHistoryList(
        request: FncyRequest<ReqRecentTransactionHistory>
    ): Result<List<TransferHistory>?> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestRecentTransferHistoryList(
                request.accessToken.toHeader(),
                request.params
            )
        )

        result.items
    }

    override suspend fun requestTransferHistoryList(
        request: FncyRequest<ReqTransferHistory>
    ): Result<ResponseWithPaging<List<TransferHistory>?>> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestTransferHistoryList(
                request.accessToken.toHeader(),
                request.params
            )
        )

        ResponseWithPaging(
            result.items,
            requireNotNull(result.paging)
        )
    }

    override suspend fun requestTransferDetailBySeq(
        request: FncyRequest<ReqTransferDetail>
    ): Result<TransferHistory?> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestTransferDetailBySeq(
                request.accessToken.toHeader(),
                request.params
            )
        )

        result.items?.first()
    }

    override suspend fun requestMessageSign(
        request: FncyRequest<ReqPostWalletSign>
    ): Result<String> = withContextRun(ioDispatcher) {
        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptPin = encryptDataManager.encrypt(
            plainData = request.params.pinNumber,
            rsaKey = rsaKey,
            count = 1
        )
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestMessageSign(
                request.accessToken.toHeader(),
                request.params.wid,
                ReqMessageSign(
                    dataToSign = request.params.dataToSign,
                    signType = request.params.signType,
                    rsaEncryptedHashedPin = encryptPin
                )
            )
        )

        result.signature
            ?: throw IllegalStateException("Signature is null")
    }

}