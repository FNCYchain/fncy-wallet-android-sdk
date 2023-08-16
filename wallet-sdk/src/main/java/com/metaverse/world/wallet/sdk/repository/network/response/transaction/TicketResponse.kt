package com.metaverse.world.wallet.sdk.repository.network.response.transaction

import com.metaverse.world.wallet.sdk.repository.network.serialization.BigIntegerSerializer
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
internal data class TicketResponse(
    val wid: Long = 0,
    val signatureType: String? = null,
    val transferEvent: String? = null,
    val transferFrom: String? = null,
    val transferTo: String? = null,
    @Serializable(with = BigIntegerSerializer::class)
    val transferVal: BigInteger? = null,
    val transferMethod: String? = null,
    val txNonce: Long = 0,
    @Serializable(with = BigIntegerSerializer::class)
    val txGasPrice: BigInteger? = null,
    @Serializable(with = BigIntegerSerializer::class)
    val txGasLimit: BigInteger? = null,
    val txInput: String? = null,
    val contractAddress: String? = null,
    val assetId: Long = 0,
    val nftId: Long = 0,
    val tokenId: Long = 0,
    val chainId: Long = 0,
    @Serializable(with = BigIntegerSerializer::class)
    val maxPriorityPerGas: BigInteger? = null,
    @Serializable(with = BigIntegerSerializer::class)
    val maxFeePerGas: BigInteger? = null,
    val displayFee: String? = null,
    val displayFeeUsd: String? = null,
    val displayFeeKrw: String? = null,
    val displayTransferVal: String? = null,
    val displayTransferValUsd: String? = null,
    val displayTransferValKrw: String? = null,
    val ticketHash: String? = null,
    val web3Code: Long = -1L,
    val web3Message: String? = null,
    val web3Data: String? = null,
    val errorCode: String? = null,
    val errorMessage: String? = null
)

internal fun TicketResponse.asDomain() = com.metaverse.world.wallet.sdk.model.transaction.FncyTicket(
    wid = wid,
    signatureType = signatureType,
    transferFrom = transferFrom,
    transferTo = transferTo,
    transferVal = transferVal,
    txNonce = txNonce,
    txGasPrice = txGasPrice,
    txGasLimit = txGasLimit,
    txInput = txInput,
    contractAddress = contractAddress,
    assetId = assetId,
    nftId = nftId,
    tokenId = tokenId,
    chainId = chainId,
    maxPriorityPerGas = maxPriorityPerGas,
    maxFeePerGas = maxFeePerGas,
    ticketHash = ticketHash,
)

