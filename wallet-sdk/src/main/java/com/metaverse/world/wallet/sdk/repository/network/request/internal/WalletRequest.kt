package com.metaverse.world.wallet.sdk.repository.network.request.internal

import com.metaverse.world.wallet.sdk.repository.network.response.Paging

@JvmInline
internal value class ReqWalletId(
    val wid: Long
)

@kotlinx.serialization.Serializable
internal data class ReqPasswordValidation(val rsaEncryptedHashedPin: String)

@kotlinx.serialization.Serializable
internal data class ReqWalletCreation(
    val chainId: Int = 1,
    val walletNm: String = "",
    val rsaEncryptUserPin: String
)

@kotlinx.serialization.Serializable
internal data class ReqPasswordChange(
    val rsaEncryptUserPin: String,
    val rsaEncryptChangeUserPin: String,
    val simplePinYn: String = "Y"
)

@kotlinx.serialization.Serializable
internal data class ReqAnswerValidation(
    val rsaEncryptedHashedAnswer: String,
    val rsaEncryptedHashedAnswerAlter: String
)

@kotlinx.serialization.Serializable
internal data class ReqPasswordChangeWithAnswer(
    val rsaEncryptUserAnswer: String,
    val rsaEncryptUserAnswerAlter: String,
    val rsaEncryptChangeUserPin: String,
    val simplePinYn: String = "Y"
)

@kotlinx.serialization.Serializable
internal data class ReqWalletAnswerCreation(
    val rsaEncryptUserQuestion: String,
    val rsaEncryptUserAnswer: String,
    val rsaEncryptUserPin: String
)

@JvmInline
internal value class ReqSmartGasPrice(
    val blockchainId: Long,
)

internal data class ReqRecentTransactionHistory(
    val wid: Long,
    val limit: Long
)

@kotlinx.serialization.Serializable
internal data class ReqMessageSign(
    val dataToSign: String,
    val signType: String = "ethSignV2",
    val rsaEncryptedHashedPin: String
)

internal data class ReqTransferHistory(
    val wid: Long,
    val assetId: Long,
    val inOut: String? = null,
    val paging: Paging = Paging()
)

internal data class ReqTransferDetail(
    val wid: Long,
    val historySeq: Long
)