package com.metaverse.world.wallet.sdk.repository.network.response.transaction

import com.metaverse.world.wallet.sdk.model.transaction.FncyTransaction
import com.metaverse.world.wallet.sdk.repository.network.response.asset.AssetInfoResponse
import com.metaverse.world.wallet.sdk.repository.network.response.asset.asDomain

@kotlinx.serialization.Serializable
internal data class TransactionResponse(
    val historySeq: Long,
    val transferSeq: Long,
    val wid: Long,
    val walletAddress: String,
) {
    var chainId: Long? = null
    var fid: Long? = null
    var assetInfo: AssetInfoResponse? = null
    var transferEventDcd: String? = null
    var transferEvent: String? = null
    var transferMethod: String? = null
    var inOutDcd: String? = null
    var inOut: String? = null
    var txStDcd: String? = null
    var txSt: String? = null
    var transferFrom: String? = null
    var transferTo: String? = null
    var transferVal: String? = null
    var displayTransferVal: String? = null
    var blockTimestamp: Long? = null
    var txAnnotation: String? = null
    var historyUts: Long? = null
    var txId: String? = null
    var txIndex: Long? = null
    var txNonce: Long? = null
    var txVal: String? = null
    var txFee: String? = null
    var displayTxFee: String? = null
    var historyKst: String? = null
}

internal fun TransactionResponse.asDomain() = FncyTransaction(
    historySeq = historySeq,
    wid = wid,
    walletAddress = walletAddress,
).also {
    it.chainId = chainId
    it.assetInfo = assetInfo?.asDomain()
    it.transferEventDcd = transferEventDcd
    it.transferEvent = transferEvent
    it.transferMethod = transferMethod
    it.inOutDcd = inOutDcd
    it.inOut = inOut
    it.txStDcd = txStDcd
    it.txSt = txSt
    it.transferFrom = transferFrom
    it.transferTo = transferTo
    it.transferVal = transferVal
    it.displayTransferVal = displayTransferVal
    it.blockTimestamp = blockTimestamp
    it.txAnnotation = txAnnotation
    it.historyUts = historyUts
    it.txId = txId
    it.txIndex = txIndex
    it.txNonce = txNonce
    it.txVal = txVal
    it.txFee = txFee
    it.displayTxFee = displayTxFee
    it.historyKst = historyKst
}