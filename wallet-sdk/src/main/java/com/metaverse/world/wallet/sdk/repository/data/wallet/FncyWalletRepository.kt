package com.metaverse.world.wallet.sdk.repository.data.wallet

import com.metaverse.world.wallet.sdk.crypto.FncyEncryptManager
import com.metaverse.world.wallet.sdk.model.request.ReqCheckResetAnswer
import com.metaverse.world.wallet.sdk.model.request.ReqCheckWalletPin
import com.metaverse.world.wallet.sdk.model.request.ReqMakeWallet
import com.metaverse.world.wallet.sdk.model.request.ReqPostWalletSign
import com.metaverse.world.wallet.sdk.model.request.ReqRegisterRestorationKey
import com.metaverse.world.wallet.sdk.model.request.ReqResetWalletPin
import com.metaverse.world.wallet.sdk.model.request.ReqSendRestoreAnswer
import com.metaverse.world.wallet.sdk.model.transaction.FncyTransaction
import com.metaverse.world.wallet.sdk.model.wallet.FncyBalance
import com.metaverse.world.wallet.sdk.model.wallet.FncyGasPrice
import com.metaverse.world.wallet.sdk.model.wallet.FncyQuestion
import com.metaverse.world.wallet.sdk.model.wallet.FncyWallet
import com.metaverse.world.wallet.sdk.repository.data.account.FncyAccountDataSource
import com.metaverse.world.wallet.sdk.repository.network.parser.ApiResultParser
import com.metaverse.world.wallet.sdk.repository.network.request.FncyRequest
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqAnswerValidation
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqMessageSign
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqPasswordChange
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqPasswordChangeWithAnswer
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqPasswordValidation
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqRecentTransactionHistory
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqSmartGasPrice
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransferDetail
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransferHistory
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqWalletAnswerCreation
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqWalletCreation
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqWalletId
import com.metaverse.world.wallet.sdk.repository.network.response.Paging
import com.metaverse.world.wallet.sdk.repository.network.response.PagingData
import com.metaverse.world.wallet.sdk.repository.network.response.toParamMap
import com.metaverse.world.wallet.sdk.repository.network.response.transaction.asDomain
import com.metaverse.world.wallet.sdk.repository.network.response.wallet.asDomain
import com.metaverse.world.wallet.sdk.utils.PinValidator
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
        request: FncyRequest<ReqSendRestoreAnswer>
    ): Result<Unit>

    // 복원키 질문 리스트 요청
    suspend fun requestWalletQuestions(
        request: FncyRequest<Paging>
    ): Result<PagingData<List<FncyQuestion>>>

    // 복원키 생성
    suspend fun requestWalletAnswerCreation(
        request: FncyRequest<ReqRegisterRestorationKey>
    ): Result<Unit>

    // 가스 수수료 요청
    suspend fun requestSmartGasPrice(
        request: FncyRequest<ReqSmartGasPrice>
    ): Result<FncyGasPrice>

    suspend fun requestRecentTransferHistoryList(
        request: FncyRequest<ReqRecentTransactionHistory>
    ): Result<List<FncyTransaction>?>

    suspend fun requestTransferHistoryList(
        request: FncyRequest<ReqTransferHistory>
    ): Result<PagingData<List<FncyTransaction>?>>

    suspend fun requestTransferDetailBySeq(
        request: FncyRequest<ReqTransferDetail>
    ): Result<FncyTransaction?>

    suspend fun requestMessageSign(
        request: FncyRequest<ReqPostWalletSign>
    ): Result<String>
}

internal class FncyWalletRepositoryImpl(
    private val fncyWalletDataSource: FncyWalletDataSource,
    private val fncyAccountDataSource: FncyAccountDataSource,
    private val apiResultParser: ApiResultParser,
    private val fncyEncryptManager: FncyEncryptManager,
    private val pinValidator: PinValidator,
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

        result.items?.getOrNull(0)?.asDomain()
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

        result.items?.getOrNull(0)?.asDomain()
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

        requireNotNull(result.items?.first()?.asDomain())
    }
    override suspend fun requestPasswordValidation(
        request: FncyRequest<ReqCheckWalletPin>
    ): Result<Unit> = withContextRun(ioDispatcher) {
        check(pinValidator.valid(request.params.pinNumber)) {
            "Invalid pin number."
        }

        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptPin = fncyEncryptManager.encrypt(
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
        check(pinValidator.valid(request.params.pinNumber)) {
            "Invalid pin number."
        }

        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptPin = fncyEncryptManager.encrypt(
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
        check(pinValidator.valid(request.params.oldPinNumber)) {
            "Invalid old pin number."
        }
        check(pinValidator.valid(request.params.newPinNumber)) {
            "Invalid new pin number."
        }

        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptPin = fncyEncryptManager.encrypt(
            plainData = request.params.oldPinNumber,
            rsaKey = rsaKey,
            count = 1
        )

        val encryptNewPin = fncyEncryptManager.encrypt(
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

        result.items?.first()?.asDomain()
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

        val encryptAnswer = fncyEncryptManager.encrypt(
            plainData = request.params.answer.lowercase(),
            rsaKey = rsaKey,
            count = 2
        )

        val encryptAnswerAlter = fncyEncryptManager.encrypt(
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
        request: FncyRequest<ReqSendRestoreAnswer>
    ): Result<Unit> = withContextRun(ioDispatcher) {
        check(pinValidator.valid(request.params.newPinNumber)) {
            "Invalid pin number."
        }
        val rsaKey = apiResultParser.parse(
                fncyAccountDataSource.requestRsaKey(
                    request.accessToken.toHeader()
                )
        ).userRsaPubKey

        val encryptAnswer = fncyEncryptManager.encrypt(
            plainData = request.params.answer.lowercase(),
            rsaKey = rsaKey,
            count = 1
        )
        val encryptAnswerAlter = fncyEncryptManager.encrypt(
            plainData = request.params.answer,
            rsaKey = rsaKey,
            count = 1
        )
        val encryptNewPin = fncyEncryptManager.encrypt(
            plainData = request.params.newPinNumber,
            rsaKey = rsaKey,
            count = 1
        )
        apiResultParser.parse(
            fncyWalletDataSource.requestWalletPasswordChangeWithAnswer(
                request.accessToken.toHeader(),
                ReqPasswordChangeWithAnswer(
                    rsaEncryptUserAnswer = encryptAnswer,
                    rsaEncryptUserAnswerAlter = encryptAnswerAlter,
                    rsaEncryptChangeUserPin = encryptNewPin
                )
            )
        )
    }

    override suspend fun requestWalletQuestions(
        request: FncyRequest<Paging>
    ): Result<PagingData<List<FncyQuestion>>> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestWalletQuestions(
                request.accessToken.toHeader(),
                request.params.toParamMap()
            )
        )

        PagingData(
            result.items?.map { it.asDomain() }
                ?: throw IllegalStateException("Question is null"),
            requireNotNull(result.paging)
        )
    }

    override suspend fun requestWalletAnswerCreation(
        request: FncyRequest<ReqRegisterRestorationKey>
    ): Result<Unit> = withContextRun(ioDispatcher) {
        check(pinValidator.valid(request.params.pinNumber)) {
            "Invalid pin number."
        }

        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptQuestion = fncyEncryptManager.encrypt(
            plainData = request.params.userQuestionSeq.toString(),
            rsaKey = rsaKey,
            count = 1
        )

        val encryptAnswer = fncyEncryptManager.encrypt(
            plainData = request.params.userAnswer,
            rsaKey = rsaKey,
            count = 1
        )

        val encryptPin = fncyEncryptManager.encrypt(
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
    ): Result<FncyGasPrice> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestSmartGasPrice(
                request.accessToken.toHeader(),
                request.params.blockchainId
            )
        )

        result.items?.first()?.asDomain()
            ?: throw IllegalStateException("Gas Price is null")
    }

    override suspend fun requestRecentTransferHistoryList(
        request: FncyRequest<ReqRecentTransactionHistory>
    ): Result<List<FncyTransaction>?> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestRecentTransferHistoryList(
                request.accessToken.toHeader(),
                request.params
            )
        )

        result.items?.map { it.asDomain() }
    }

    override suspend fun requestTransferHistoryList(
        request: FncyRequest<ReqTransferHistory>
    ): Result<PagingData<List<FncyTransaction>?>> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestTransferHistoryList(
                request.accessToken.toHeader(),
                request.params
            )
        )

        PagingData(
            result.items?.map { it.asDomain() }
                ?: throw IllegalStateException("Transaction is null"),
            requireNotNull(result.paging)
        )
    }

    override suspend fun requestTransferDetailBySeq(
        request: FncyRequest<ReqTransferDetail>
    ): Result<FncyTransaction?> = withContextRun(ioDispatcher) {
        val result = apiResultParser.parse(
            fncyWalletDataSource.requestTransferDetailBySeq(
                request.accessToken.toHeader(),
                request.params
            )
        )

        result.items?.first()?.asDomain()
    }

    override suspend fun requestMessageSign(
        request: FncyRequest<ReqPostWalletSign>
    ): Result<String> = withContextRun(ioDispatcher) {
        check(pinValidator.valid(request.params.pinNumber)) {
            "Invalid pin number."
        }

        val rsaKey = apiResultParser.parse(
            fncyAccountDataSource.requestRsaKey(
                request.accessToken.toHeader()
            )
        ).userRsaPubKey

        val encryptPin = fncyEncryptManager.encrypt(
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