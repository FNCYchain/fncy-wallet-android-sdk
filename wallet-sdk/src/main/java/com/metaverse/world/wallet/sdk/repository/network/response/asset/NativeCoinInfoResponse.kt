package com.metaverse.world.wallet.sdk.repository.network.response.asset

import com.metaverse.world.wallet.sdk.model.asset.FncyNativeCoinInfo

@kotlinx.serialization.Serializable
internal data class NativeCoinInfoResponse(
    val nativeCoinId: Long = 0,
    val nativeCoinSymbol: String? = null,
    val nativeCoinSymbolImg: String? = null,
    val nativeCoinDecimal: Long = 0
)

internal fun NativeCoinInfoResponse.asDomain() = FncyNativeCoinInfo(
    nativeCoinId = nativeCoinId,
    nativeCoinSymbol = nativeCoinSymbol,
    nativeCoinSymbolImg = nativeCoinSymbolImg,
    nativeCoinDecimal = nativeCoinDecimal
)