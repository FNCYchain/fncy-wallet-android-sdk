package com.metaverse.world.wallet.sdk.model.transaction

import com.metaverse.world.wallet.sdk.model.etc.TicketType
import java.math.BigInteger

data class FncyTicket(
    val wid: Long = 0,
    val signatureType: TicketType? = null,
    val transferFrom: String? = null,
    val transferTo: String? = null,
    val transferVal: BigInteger? = null,
    val txNonce: Long = 0,
    val txGasPrice: BigInteger? = null,
    val txGasLimit: BigInteger? = null,
    val txInput: String? = null,
    val contractAddress: String? = null,
    val assetId: Long = 0,
    val nftId: Long = 0,
    val tokenId: Long = 0,
    val chainId: Long = 0,
    val maxPriorityPerGas: BigInteger? = null,
    val maxFeePerGas: BigInteger? = null,
    val ticketHash: String? = null,
)