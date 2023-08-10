package com.metaverse.world.wallet.sdk

import android.app.Application
import com.metaverse.world.wallet.sdk.model.etc.NFTOption
import com.metaverse.world.wallet.sdk.model.etc.NFTType
import com.metaverse.world.wallet.sdk.model.etc.SignType
import com.metaverse.world.wallet.sdk.model.etc.TicketType
import com.metaverse.world.wallet.sdk.model.request.FncyRequest
import com.metaverse.world.wallet.sdk.model.request.internal.ReqCheckResetAnswer
import com.metaverse.world.wallet.sdk.model.request.internal.ReqCheckWalletPin
import com.metaverse.world.wallet.sdk.model.request.internal.ReqMakeWallet
import com.metaverse.world.wallet.sdk.model.request.internal.ReqPostWalletSign
import com.metaverse.world.wallet.sdk.model.request.internal.ReqRegisterRestorationKey
import com.metaverse.world.wallet.sdk.model.request.internal.ReqResetWalletPin
import com.metaverse.world.wallet.sdk.model.request.internal.ReqSendTransaction
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqAssetByAssetId
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqAssetList
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqBlockchainAsset
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqBlockchainAssetByContractAddress
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqNftAssetByNftId
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqNftAssetByOption
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqRecentTransactionHistory
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqRemoveDeviceToken
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqSmartGasPrice
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransaction
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransactionByTicket
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransferDetail
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqTransferHistory
import com.metaverse.world.wallet.sdk.model.request.internal.datasource.ReqWalletId
import com.metaverse.world.wallet.sdk.repository.data.asset.FncyAssetCategory
import com.metaverse.world.wallet.sdk.repository.network.response.Paging
import java.math.BigInteger

class FncyWalletSDK constructor(
    private val accessToken: String
) {
    private val walletRepo = FncyWalletCore.walletRepository
    private val assetRepo = FncyWalletCore.assetRepository
    private val accountRepo = FncyWalletCore.accountRepository
    private val transactionRepo = FncyWalletCore.transactionRepository

    companion object {
        @JvmStatic
        fun initSDK(application: Application) {
            FncyWalletCore.initialize(application)
        }
    }

    /**************************
     *         Account        *
     **************************/

    suspend fun insertUser() = accountRepo.insertUser(
        FncyRequest(
            accessToken = accessToken,
            params = Unit
        )
    )

    suspend fun logoutUser(
        uuid: String
    ) = accountRepo.requestRemoveDeviceToken(
        FncyRequest(
            accessToken = accessToken,
            params = ReqRemoveDeviceToken(
                uuid = uuid
            )
        )
    )

    suspend fun getRSAKey() = accountRepo.requestRsaKey(
        FncyRequest(
            accessToken = accessToken,
            params = Unit
        )
    )

    /**************************
     *         Wallet         *
     **************************/

    suspend fun getWallet() = walletRepo.requestWallet(
        FncyRequest(
            accessToken = accessToken,
            params = Unit
        )
    )

    suspend fun getWalletAllBalance(
        wid: Long
    ) = walletRepo.requestTotalWalletBalance(
        FncyRequest(
            accessToken = accessToken,
            params = ReqWalletId(
                wid = wid
            )
        )
    )

    suspend fun makeWallet(
        walletNm: String,
        pinNumber: String
    ) = walletRepo.requestWalletCreation(
        FncyRequest(
            accessToken = accessToken,
            params = ReqMakeWallet(
                walletName = walletNm,
                pinNumber = pinNumber
            )
        )
    )

    suspend fun postRegisterRestorationKey(
        wid: Long,
        userQuestionSeq: Int,
        userAnswer: String,
        pinNumber: String
    ) = walletRepo.requestWalletAnswerCreation(
        FncyRequest(
            accessToken = accessToken,
            params = ReqRegisterRestorationKey(
                wid = wid,
                userQuestionSeq = userQuestionSeq,
                userAnswer = userAnswer,
                pinNumber = pinNumber
            )
        )
    )

    suspend fun checkWalletPinNumber(
        pinNumber: String,
        excludeHistoryYn: String = "N"
    ) = walletRepo.requestPasswordValidation(
        FncyRequest(
            accessToken = accessToken,
            params = ReqCheckWalletPin(
                pinNumber = pinNumber,
                excludeHistoryYn = excludeHistoryYn
            )
        )
    )

    suspend fun resetWalletPinNumber(
        oldPinNumber: String,
        newPinNumber: String
    ) = walletRepo.requestWalletPasswordChange(
        FncyRequest(
            accessToken = accessToken,
            params = ReqResetWalletPin(
                oldPinNumber = oldPinNumber,
                newPinNumber = newPinNumber
            )
        )
    )

    suspend fun getQuestionList(
        pageNo: Int = 1,
        pageSize: Int = 20
    ) = walletRepo.requestWalletQuestions(
        FncyRequest(
            accessToken = accessToken,
            params = Paging(
                pageNo = pageNo,
                pageSize = pageSize
            )
        )
    )

    suspend fun checkResetAnswer(
        answer: String
    ) = walletRepo.requestWalletAnswerValidation(
        FncyRequest(
            accessToken = accessToken,
            params = ReqCheckResetAnswer(
                answer = answer
            )
        )
    )

    suspend fun getResetQuestion(
    ) = walletRepo.requestChosenWalletQuestion(
        FncyRequest(
            accessToken = accessToken,
            params = Unit
        )
    )

    suspend fun postWalletSign(
        wid: Long,
        dataToSign: String,
        pinNumber: String,
        signType: SignType = SignType.EthSign
    ) = walletRepo.requestMessageSign(
        FncyRequest(
            accessToken = accessToken,
            params = ReqPostWalletSign(
                wid = wid,
                dataToSign = dataToSign,
                pinNumber = pinNumber,
                signType = signType.value
            )
        )
    )

    suspend fun getGasPrice(
        chainId: Long
    ) = walletRepo.requestSmartGasPrice(
        FncyRequest(
            accessToken = accessToken,
            params = ReqSmartGasPrice(
                blockchainId = chainId
            )
        )
    )

    suspend fun getRecentAddress(
        wid: Long
    ) = walletRepo.requestRecentTransferHistoryList(
        FncyRequest(
            accessToken = accessToken,
            params = ReqRecentTransactionHistory(
                wid = wid,
                limit = 5
            )
        )
    )

    suspend fun getTransferHistoryList(
        wid: Long,
        assetId: Long,
        inOut: String? = null,
    ) = walletRepo.requestTransferHistoryList(
        FncyRequest(
            accessToken = accessToken,
            params = ReqTransferHistory(
                wid = wid,
                assetId = assetId,
                inOut = inOut
            )
        )
    )

    suspend fun getTransferHistoryDetail(
        wid: Long,
        historySeq: Long
    ) = walletRepo.requestTransferDetailBySeq(
        FncyRequest(
            accessToken = accessToken,
            params = ReqTransferDetail(
                wid = wid,
                historySeq = historySeq
            )
        )
    )



    /**************************
     *          Asset         *
     **************************/

    suspend fun getNativeCoinInfo(
        chainId: Long
    ) = assetRepo.requestBlockchainPlatformAssetId(
        FncyRequest(
            accessToken = accessToken,
            params = ReqBlockchainAsset(
                chainId = chainId
            )
        )
    )

    suspend fun getContractInfo(
        chainId: Long,
        contractAddress: String
    ) = assetRepo.requestBlockchainPlatformAssetList(
        FncyRequest(
            accessToken = accessToken,
            params = ReqBlockchainAssetByContractAddress(
                chainId = chainId,
                contractAddress = contractAddress
            )
        )
    )

    suspend fun getFncyInfo() = assetRepo.requestAssetCurrency(
        FncyRequest(
            accessToken = accessToken,
            params = Unit
        )
    )

    suspend fun getAssetList(
        wid: Long,
        category: FncyAssetCategory? = null
    ) = assetRepo.requestAssetList(
        FncyRequest(
            accessToken = accessToken,
            params = ReqAssetList(
                wid = wid,
                fncyAssetCategory = category
            )
        )
    )

    suspend fun getAssetById(
        wid: Long,
        assetId: Long
    ) = assetRepo.requestAssetByAssetId(
        FncyRequest(
            accessToken = accessToken,
            params = ReqAssetByAssetId(
                wid = wid,
                assetId = assetId
            )
        )
    )

    suspend fun getNFTList(
        wid: Long,
        option: NFTOption? = null,
        nftType: NFTType? = null
    ) = assetRepo.requestNftsByOption(
        FncyRequest(
            accessToken = accessToken,
            params = ReqNftAssetByOption(
                wid = wid,
                option = option,
                nftType = nftType
            )
        )
    )

    suspend fun getNFTById(
        wid: Long,
        nftId: Long
    ) = assetRepo.requestNftAssetByNftId(
        FncyRequest(
            accessToken = accessToken,
            params = ReqNftAssetByNftId(
                wid = wid,
                nftId = nftId
            )
        )
    )

    /**************************
     *      Transaction       *
     **************************/

    suspend fun estimateTicket(
        wid: Long,
        signatureType: TicketType,
        assetId: Long,
        chainId: Long,
        transferTo: String,
        transferEvent: String? = null,
        transferFrom: String? = null,
        transferVal: BigInteger,
        contractAddress: String? = null,
        nftId: Long? = null,
        txNonce: Long = 0,
        txInput: String? = null,
        txGasPrice: BigInteger = BigInteger.ZERO,
        txGasLimit: BigInteger = BigInteger.ZERO,
        tokenId: Long = 0,
        maxPriorityPerGas: BigInteger = BigInteger.ZERO,
        maxFeePerGas: BigInteger = BigInteger.ZERO
    ) = transactionRepo.requestTransactionEstimateInfo(
        FncyRequest(
            accessToken = accessToken,
            params = ReqTransaction(
                wid = wid,
                signatureType = signatureType.value,
                assetId = assetId,
                chainId = chainId,
                transferTo = transferTo,
                transferEvent = transferEvent,
                transferFrom = transferFrom,
                transferVal = transferVal,
                transferMethod = null,
                contractAddress = contractAddress,
                nftId = nftId,
                txNonce = txNonce,
                txInput = txInput,
                txGasPrice = txGasPrice,
                txGasLimit = txGasLimit,
                tokenId = tokenId,
                maxPriorityPerGas = maxPriorityPerGas,
                maxFeePerGas = maxFeePerGas
            )
        )
    )

    suspend fun makeTicket(
        wid: Long,
        signatureType: TicketType,
        assetId: Long,
        chainId: Long,
        transferTo: String,
        transferEvent: String? = null,
        transferFrom: String? = null,
        transferVal: BigInteger,
        transferMethod: String? = null,
        contractAddress: String? = null,
        nftId: Long? = null,
        txNonce: Long = 0,
        txInput: String? = null,
        txGasPrice: BigInteger = BigInteger.ZERO,
        txGasLimit: BigInteger = BigInteger.ZERO,
        tokenId: Long = 0,
        maxPriorityPerGas: BigInteger = BigInteger.ZERO,
        maxFeePerGas: BigInteger = BigInteger.ZERO
    ) = transactionRepo.requestTransactionTicket(
        FncyRequest(
            accessToken = accessToken,
            params = ReqTransaction(
                wid = wid,
                signatureType = signatureType.value,
                assetId = assetId,
                chainId = chainId,
                transferTo = transferTo,
                transferEvent = transferEvent,
                transferFrom = transferFrom,
                transferVal = transferVal,
                transferMethod = transferMethod,
                contractAddress = contractAddress,
                nftId = nftId,
                txNonce = txNonce,
                txInput = txInput,
                txGasPrice = txGasPrice,
                txGasLimit = txGasLimit,
                tokenId = tokenId,
                maxPriorityPerGas = maxPriorityPerGas,
                maxFeePerGas = maxFeePerGas
            )
        )
    )

    suspend fun sendTicket(
        ticketUuid: String,
        pinNumber: String
    ) = transactionRepo.requestTransactionTicketSend(
        FncyRequest(
            accessToken = accessToken,
            params = ReqSendTransaction(
                ticketUUID = ticketUuid,
                pinNumber = pinNumber
            )
        )
    )

    suspend fun getTicketInfo(
        ticketUUID: String
    ) = transactionRepo.requestTransactionByTicket(
        FncyRequest(
            accessToken = accessToken,
            params = ReqTransactionByTicket(
                ticketUUID
            )
        )
    )


}