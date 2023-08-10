package com.metaverse.world.wallet.sdk.model.asset

import com.metaverse.world.wallet.sdk.repository.network.serialization.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class FncyPrices(
    val displayKrwPrice: String? = null,
    val displayUsdPrice: String? = null,
    val displayBtcPrice: String? = null,
    val displayEthPrice: String? = null
) {
    @Serializable(with = BigDecimalSerializer::class)
    var btcPrice: BigDecimal? = null
    @Serializable(with = BigDecimalSerializer::class)
    var ethPrice: BigDecimal? = null
    @Serializable(with = BigDecimalSerializer::class)
    var usdPrice: BigDecimal? = null
    @Serializable(with = BigDecimalSerializer::class)
    var krwPrice: BigDecimal? = null
}