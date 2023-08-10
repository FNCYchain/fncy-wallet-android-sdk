package com.metaverse.world.wallet.sdk.model.asset

@kotlinx.serialization.Serializable
data class FncyNativeCoinInfo(
    val nativeCoinId: Long = 0,
    val nativeCoinSymbol: String? = null,
    val nativeCoinSymbolImg: String? = null,
    val nativeCoinDecimal: Long = 0
)