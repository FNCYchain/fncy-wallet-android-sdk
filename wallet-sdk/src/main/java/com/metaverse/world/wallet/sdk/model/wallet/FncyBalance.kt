package com.metaverse.world.wallet.sdk.model.wallet

data class FncyBalance(
    val wid: Long
) {
    var totalUsdPrice: String? = null
    var displayTotalUsdPrice: String? = null
    var totalKrwPrice: String? = null
    var displayTotalKrwPrice: String? = null
}