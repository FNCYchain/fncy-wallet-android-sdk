package com.metaverse.world.wallet.sdk

import android.app.Application
import com.metaverse.world.wallet.sdk.model.etc.InOut
import com.metaverse.world.wallet.sdk.model.etc.NFTOption
import com.metaverse.world.wallet.sdk.model.etc.SignType
import com.metaverse.world.wallet.sdk.model.etc.TicketType
import com.metaverse.world.wallet.sdk.model.request.ReqCheckResetAnswer
import com.metaverse.world.wallet.sdk.model.request.ReqCheckWalletPin
import com.metaverse.world.wallet.sdk.model.request.ReqMakeWallet
import com.metaverse.world.wallet.sdk.model.request.ReqPostWalletSign
import com.metaverse.world.wallet.sdk.model.request.ReqRegisterRestorationKey
import com.metaverse.world.wallet.sdk.model.request.ReqResetWalletPin
import com.metaverse.world.wallet.sdk.model.request.ReqSendRestoreAnswer
import com.metaverse.world.wallet.sdk.model.request.ReqSendTransaction
import com.metaverse.world.wallet.sdk.repository.data.asset.FncyAssetCategory
import com.metaverse.world.wallet.sdk.repository.network.request.FncyRequest
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqAssetByAssetId
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqAssetList
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqBlockchainAssetByContractAddress
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqBlockchainInfo
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqNftAssetByNftId
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqNftAssetByOption
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqRecentTransactionHistory
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqRemoveDeviceToken
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqSmartGasPrice
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransaction
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransactionByTicket
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransferDetail
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqTransferHistory
import com.metaverse.world.wallet.sdk.repository.network.request.internal.ReqWalletId
import com.metaverse.world.wallet.sdk.repository.network.response.Paging
import java.math.BigInteger

class FncyWalletSDK constructor(
    private val accessToken: String
) {

    companion object {
        @JvmStatic
        fun initSDK(application: Application) {
            FncyWalletCore.initialize(application)
        }
    }

    /**************************
     *         Account        *
     **************************/

    suspend fun insertUser() = FncyWalletCore
        .accountRepository
        .insertUser(
            FncyRequest(
                accessToken = accessToken,
                params = Unit
            )
        )

    suspend fun logoutUser(
        uuid: String
    ) = FncyWalletCore
        .accountRepository
        .requestRemoveDeviceToken(
            FncyRequest(
                accessToken = accessToken,
                params = ReqRemoveDeviceToken(
                    uuid = uuid
                )
            )
        )

    suspend fun getRSAKey() = FncyWalletCore
        .accountRepository
        .requestRsaKey(
            FncyRequest(
                accessToken = accessToken,
                params = Unit
            )
        )

    /**************************
     *         Wallet         *
     **************************/

    suspend fun getWallet() = FncyWalletCore
        .walletRepository
        .requestWallet(
            FncyRequest(
                accessToken = accessToken,
                params = Unit
            )
        )

    suspend fun getWalletAllBalance(
        wid: Long
    ) = FncyWalletCore
        .walletRepository
        .requestTotalWalletBalance(
            FncyRequest(
                accessToken = accessToken,
                params = ReqWalletId(
                    wid = wid
                )
            )
        )

    suspend fun makeWallet(
        walletName: String,
        pinNumber: String
    ) = FncyWalletCore
        .walletRepository
        .requestWalletCreation(
            FncyRequest(
                accessToken = accessToken,
                params = ReqMakeWallet(
                    walletName = walletName,
                    pinNumber = pinNumber
                )
            )
        )

    suspend fun postRegisterRestorationKey(
        wid: Long,
        userQuestionSeq: Int,
        userAnswer: String,
        pinNumber: String
    ) = FncyWalletCore
        .walletRepository
        .requestWalletAnswerCreation(
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

    suspend fun postResetQuestion(
        answer: String,
        newPinNumber: String
    ) = FncyWalletCore
        .walletRepository
        .requestWalletPasswordChangeWithAnswer(
            FncyRequest(
                accessToken = accessToken,
                params = ReqSendRestoreAnswer(
                    answer = answer,
                    newPinNumber = newPinNumber
                )
            )
        )

    suspend fun checkWalletPinNumber(
        pinNumber: String
    ) = FncyWalletCore
        .walletRepository
        .requestPasswordValidation(
            FncyRequest(
                accessToken = accessToken,
                params = ReqCheckWalletPin(
                    pinNumber = pinNumber,
                    excludeHistoryYn = "N"
                )
            )
        )

    suspend fun postResetPinNumber(
        oldPinNumber: String,
        newPinNumber: String
    ) = FncyWalletCore
        .walletRepository
        .requestWalletPasswordChange(
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
    ) = FncyWalletCore
        .walletRepository
        .requestWalletQuestions(
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
    ) = FncyWalletCore
        .walletRepository
        .requestWalletAnswerValidation(
            FncyRequest(
                accessToken = accessToken,
                params = ReqCheckResetAnswer(
                    answer = answer
                )
            )
        )

    suspend fun getResetQuestion(
    ) = FncyWalletCore
        .walletRepository
        .requestChosenWalletQuestion(
            FncyRequest(
                accessToken = accessToken,
                params = Unit
            )
        )

    suspend fun postWalletSign(
        wid: Long,
        dataToSign: String,
        signType: SignType = SignType.EthSign,
        pinNumber: String
    ) = FncyWalletCore
        .walletRepository
        .requestMessageSign(
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
    ) = FncyWalletCore
        .walletRepository
        .requestSmartGasPrice(
            FncyRequest(
                accessToken = accessToken,
                params = ReqSmartGasPrice(
                    blockchainId = chainId
                )
            )
        )

    suspend fun getRecentAddress(
        wid: Long
    ) = FncyWalletCore
        .walletRepository
        .requestRecentTransferHistoryList(
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
        pageNo: Int = 1,
        pageSize: Int = 20,
        filter: InOut,
    ) = FncyWalletCore
        .walletRepository
        .requestTransferHistoryList(
            FncyRequest(
                accessToken = accessToken,
                params = ReqTransferHistory(
                    wid = wid,
                    assetId = assetId,
                    inOut = filter.value,
                    paging = Paging(
                        pageNo = pageNo,
                        pageSize = pageSize
                    )
                )
            )
        )

    suspend fun getTransferHistoryDetail(
        wid: Long,
        historySeq: Long
    ) = FncyWalletCore
        .walletRepository
        .requestTransferDetailBySeq(
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

    suspend fun getBlockChainInfo(
        chainId: Long
    ) = FncyWalletCore
        .assetRepository
        .requestBlockchainPlatform(
            FncyRequest(
                accessToken = accessToken,
                params = ReqBlockchainInfo(
                    chainId = chainId
                )
            )
        )

    suspend fun getContractInfo(
        chainId: Long,
        contractAddress: String
    ) = FncyWalletCore
        .assetRepository
        .requestBlockchainPlatformAssetList(
            FncyRequest(
                accessToken = accessToken,
                params = ReqBlockchainAssetByContractAddress(
                    chainId = chainId,
                    contractAddress = contractAddress
                )
            )
        )

    suspend fun getFncyInfo() = FncyWalletCore
        .assetRepository
        .requestAssetCurrency(
            FncyRequest(
                accessToken = accessToken,
                params = Unit
            )
        )

    suspend fun getAssetList(
        wid: Long
    ) = FncyWalletCore
        .assetRepository
        .requestAssetList(
            FncyRequest(
                accessToken = accessToken,
                params = ReqAssetList(
                    wid = wid,
                    fncyAssetCategory = FncyAssetCategory.FNCY
                )
            )
        )

    suspend fun getAssetById(
        wid: Long,
        assetId: Long
    ) = FncyWalletCore
        .assetRepository
        .requestAssetByAssetId(
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
        filter: NFTOption = NFTOption.All,
        pageNo: Int = 1,
        pageSize: Int = 20
    ) = FncyWalletCore
        .assetRepository
        .requestNftsByOption(
            FncyRequest(
                accessToken = accessToken,
                params = ReqNftAssetByOption(
                    wid = wid,
                    option = filter,
                    paging = Paging(
                        pageNo = pageNo,
                        pageSize = pageSize
                    )
                )
            )
        )

    suspend fun getNFTById(
        wid: Long,
        nftId: Long
    ) = FncyWalletCore
        .assetRepository
        .requestNftAssetByNftId(
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
        chainId: Long,
        signatureType: TicketType,
        toAddress: String,
        transferVal: BigInteger,
        txGasPrice: BigInteger = BigInteger.ZERO,
        txInput: String? = null,
        contractAddress: String? = null,
        assetId: Long,
        nftId: Long? = null,
        maxPriorityPerGas: BigInteger = BigInteger.ZERO,
        maxFeePerGas: BigInteger = BigInteger.ZERO,
        txNonce: Long = 0,
    ) = FncyWalletCore
        .transactionRepository
        .requestTransactionEstimateInfo(
            FncyRequest(
                accessToken = accessToken,
                params = ReqTransaction(
                    wid = wid,
                    signatureType = signatureType.value,
                    assetId = assetId,
                    chainId = chainId,
                    transferTo = toAddress,
                    transferEvent = null,
                    transferFrom = null,
                    transferVal = transferVal,
                    transferMethod = null,
                    contractAddress = contractAddress,
                    nftId = nftId,
                    txNonce = txNonce,
                    txInput = txInput,
                    txGasPrice = txGasPrice,
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
        toAddress: String,
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
    ) = FncyWalletCore
        .transactionRepository
        .requestTransactionTicket(
            FncyRequest(
                accessToken = accessToken,
                params = ReqTransaction(
                    wid = wid,
                    signatureType = signatureType.value,
                    assetId = assetId,
                    chainId = chainId,
                    transferTo = toAddress,
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
    ) = FncyWalletCore
        .transactionRepository
        .requestTransactionTicketSend(
            FncyRequest(
                accessToken = accessToken,
                params = ReqSendTransaction(
                    ticketUUID = ticketUuid,
                    pinNumber = pinNumber
                )
            )
        )

    suspend fun getTicketInfo(
        ticketUuid: String
    ) = FncyWalletCore
        .transactionRepository
        .requestTransactionByTicket(
            FncyRequest(
                accessToken = accessToken,
                params = ReqTransactionByTicket(
                    ticketUuid
                )
            )
        )

}