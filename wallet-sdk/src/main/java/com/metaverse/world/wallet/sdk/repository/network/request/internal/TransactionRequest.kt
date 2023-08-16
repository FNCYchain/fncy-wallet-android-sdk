package com.metaverse.world.wallet.sdk.repository.network.request.internal

import com.metaverse.world.wallet.sdk.repository.network.serialization.BigIntegerSerializer
import java.math.BigInteger

@JvmInline
internal value class ReqTransactionByTicket(
    val ticketUuid: String
)

@kotlinx.serialization.Serializable
internal data class ReqTransactionTicketSend(
    @kotlinx.serialization.Transient
    val ticketUuid: String = "",
    val rsaEncryptedHashedPin: String
)

@kotlinx.serialization.Serializable
internal data class ReqTransaction(
    val wid: Long,
    val signatureType: String,
    val assetId: Long,
    val chainId: Long,
    val transferTo: String,
    val transferEvent: String? = null,
    val transferFrom: String? = null,
    @kotlinx.serialization.Serializable(BigIntegerSerializer::class)
    val transferVal: BigInteger,
    val transferMethod: String? = null,
    val contractAddress: String? = null,
    val nftId: Long? = null,
    val txNonce: Long = 0,
    val txInput: String? = null,
    @kotlinx.serialization.Serializable(BigIntegerSerializer::class)
    val txGasPrice: BigInteger = BigInteger.ZERO,
    @kotlinx.serialization.Serializable(BigIntegerSerializer::class)
    val txGasLimit: BigInteger = BigInteger.ZERO,
    val tokenId: Long = 0,
    @kotlinx.serialization.Serializable(BigIntegerSerializer::class)
    val maxPriorityPerGas: BigInteger = BigInteger.ZERO,
    @kotlinx.serialization.Serializable(BigIntegerSerializer::class)
    val maxFeePerGas: BigInteger = BigInteger.ZERO,
    val web3Code: Long = -1L,
    val web3Message: String? = null,
    val web3Data: String? = null,
    val errorCode: String? = null,
    val errorMessage: String? = null
)