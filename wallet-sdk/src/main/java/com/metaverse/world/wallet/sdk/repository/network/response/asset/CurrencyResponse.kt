package com.metaverse.world.wallet.sdk.repository.network.response.asset

import com.metaverse.world.wallet.sdk.model.asset.FncyCurrency

@kotlinx.serialization.Serializable
internal data class CurrencyResponse(
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

internal fun CurrencyResponse.asDomain() = FncyCurrency(
    assetId = assetId,
).also {
    it.cmcName = cmcName
    it.slug = slug
    it.symbol = symbol
    it.btcPrice = btcPrice
    it.displayBtcPrice = displayBtcPrice
    it.ethPrice = ethPrice
    it.displayEthPrice = displayEthPrice
    it.usdPrice = usdPrice
    it.displayUsdPrice = displayUsdPrice
    it.krwPrice = krwPrice
    it.displayKrwPrice = displayKrwPrice
    it.dailyHigherUsd = dailyHigherUsd
    it.displayDailyHigherUsd = displayDailyHigherUsd
    it.dailyHigherKrw = dailyHigherKrw
    it.displayDailyHigherKrw = displayDailyHigherKrw
    it.dailyLowerUsd = dailyLowerUsd
    it.displayDailyLowerUsd = displayDailyLowerUsd
    it.dailyLowerKrw = dailyLowerKrw
    it.displayDailyLowerKrw = displayDailyLowerKrw
    it.dailyVolumeUsd = dailyVolumeUsd
    it.displayDailyVolumeUsd = displayDailyVolumeUsd
    it.dailyVolumeKrw = dailyVolumeKrw
    it.displayDailyVolumeKrw = displayDailyVolumeKrw
    it.hourChange = hourChange
    it.dayChange = dayChange
    it.projectLink = projectLink
    it.whitePaperLink = whitePaperLink
}