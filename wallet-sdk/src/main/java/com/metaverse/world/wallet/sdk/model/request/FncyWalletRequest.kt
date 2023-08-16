package com.metaverse.world.wallet.sdk.model.request

internal data class ReqMakeWallet(
    val chainId: Int = 1,
    val walletName: String = "",
    val pinNumber: String
)

internal data class ReqRegisterRestorationKey(
    val wid: Long,
    val userQuestionSeq: Int,
    val userAnswer: String,
    val pinNumber: String
)

internal data class ReqCheckWalletPin(
    val pinNumber: String,
    val excludeHistoryYn: String = "N"
)

internal data class ReqResetWalletPin(
    val oldPinNumber: String,
    val newPinNumber: String,
    val simplePinYn: String = "Y"
)

@JvmInline
internal value class ReqCheckResetAnswer(
    val answer: String,
)

internal data class ReqSendRestoreAnswer(
    val answer: String,
    val newPinNumber: String,
)

internal data class ReqPostWalletSign(
    val wid: Long,
    val dataToSign: String,
    val pinNumber: String,
    val signType: String = "ethSignV2"
)

internal data class ReqSendTransaction(
    val ticketUUID: String,
    val pinNumber: String
)