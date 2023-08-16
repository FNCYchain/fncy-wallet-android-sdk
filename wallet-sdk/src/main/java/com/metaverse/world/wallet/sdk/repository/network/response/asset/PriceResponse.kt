package com.metaverse.world.wallet.sdk.repository.network.response.asset

import com.metaverse.world.wallet.sdk.model.asset.FncyPrice
import com.metaverse.world.wallet.sdk.repository.network.serialization.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
internal data class PriceResponse(
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

internal fun PriceResponse.asDomain() = FncyPrice(
    displayKrwPrice = displayKrwPrice,
    displayUsdPrice = displayUsdPrice,
    displayBtcPrice = displayBtcPrice,
    displayEthPrice = displayEthPrice
).also {
    it.btcPrice = btcPrice
    it.ethPrice = ethPrice
    it.usdPrice = usdPrice
    it.krwPrice = krwPrice
}