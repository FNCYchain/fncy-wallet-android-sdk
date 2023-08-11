package com.metaverse.world.wallet.sdk.model.asset

import com.metaverse.world.wallet.sdk.repository.network.serialization.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class FncyAsset(
    val assetId: Long,
    val chainId: Long
) {
    var assetOrd: Long = 0
    var displayYn: String? = null
    @Serializable(with = BigDecimalSerializer::class)
    var balance: BigDecimal? = null
    var balancePlainString: String? = null
    var displayBalance: String? = null
    var price: FncyPrice? = null
    var assetInfo: FncyAssetInfo? = null
    var gameCode: String? = null
    var gameNm: String? = null
    var marketId: String? = null
    var publishedYn: String? = null
}