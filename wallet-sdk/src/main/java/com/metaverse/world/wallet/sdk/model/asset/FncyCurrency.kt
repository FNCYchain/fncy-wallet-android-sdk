package com.metaverse.world.wallet.sdk.model.asset

data class FncyCurrency(
    val assetId: Long
) {
    var cmcName: String? = null
    var slug: String? = null
    var symbol: String? = null
    var btcPrice: String? = null
    var displayBtcPrice: String? = null
    var ethPrice: String? = null
    var displayEthPrice: String? = null
    var usdPrice: String? = null
    var displayUsdPrice: String? = null
    var krwPrice: String? = null
    var displayKrwPrice: String? = null
    var dailyHigherUsd: String? = null
    var displayDailyHigherUsd: String? = null
    var dailyHigherKrw: String? = null
    var displayDailyHigherKrw: String? = null
    var dailyLowerUsd: String? = null
    var displayDailyLowerUsd: String? = null
    var dailyLowerKrw: String? = null
    var displayDailyLowerKrw: String? = null
    var dailyVolumeUsd: String? = null
    var displayDailyVolumeUsd: String? = null
    var dailyVolumeKrw: String? = null
    var displayDailyVolumeKrw: String? = null
    var hourChange: String? = null
    var dayChange: String? = null
    var projectLink: String? = null
    var whitePaperLink: String? = null
}