package com.metaverse.world.wallet.sdk.model.asset

import java.math.BigDecimal

data class FncyPrice(
    val displayKrwPrice: String? = null,
    val displayUsdPrice: String? = null,
    val displayBtcPrice: String? = null,
    val displayEthPrice: String? = null
) {
    var btcPrice: BigDecimal? = null
    var ethPrice: BigDecimal? = null
    var usdPrice: BigDecimal? = null
    var krwPrice: BigDecimal? = null
}