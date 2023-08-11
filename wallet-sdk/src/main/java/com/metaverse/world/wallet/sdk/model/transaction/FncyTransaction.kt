package com.metaverse.world.wallet.sdk.model.transaction

import com.metaverse.world.wallet.sdk.model.asset.FncyAssetInfo

@kotlinx.serialization.Serializable
data class FncyTransaction(
    val historySeq: Long,
    val transferSeq: Long,
    val wid: Long,
    val walletAddress: String,
) {
    var chainId: Long? = null
    var fid: Long? = null
    var assetInfo: FncyAssetInfo? = null
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
