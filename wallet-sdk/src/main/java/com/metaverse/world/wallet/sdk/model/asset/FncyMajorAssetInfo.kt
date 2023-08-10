package com.metaverse.world.wallet.sdk.model.asset

@kotlinx.serialization.Serializable
data class FncyMajorAssetInfo(
    val majorAssetId: Long = 0,
    val majorAssetSymbol: String? = null,
    val majorAssetSymbolImg: String? = null,
    val totalBalance: String? = null,
    val displayTotalBalance: String? = null,
    val totalUsdPrice: String? = null,
    val displayTotalUsdPrice: String? = null,
    val totalKrwPrice: String? = null,
    val displayTotalKrwPrice: String? = null
)