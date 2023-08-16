package com.metaverse.world.wallet.sdk.model.wallet

data class FncyWallet(
    val wid: Long,
    val chainId: Int,
    val walletAddress: String,
    val walletTypeDcd: String,
    val secureLevel: Int,
    val createKst: Long = 0
) {
    var updateKst: Long = 0
    var walletType: String? = null
    var walletNm: String = ""
    var nftCount: Int = 0
}