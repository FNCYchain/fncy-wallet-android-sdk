package com.metaverse.world.wallet.sdk.repository.network.response.asset

import com.metaverse.world.wallet.sdk.model.asset.FncyAsset
import com.metaverse.world.wallet.sdk.repository.network.serialization.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
internal data class AssetResponse(
    val assetId: Long,
    val chainId: Long
) {
    var assetOrd: Long = 0
    var displayYn: String? = null
    @Serializable(with = BigDecimalSerializer::class)
    var balance: BigDecimal? = null
    var balancePlainString: String? = null
    var displayBalance: String? = null
    var price: PriceResponse? = null
    var assetInfo: AssetInfoResponse? = null
    var gameCode: String? = null
    var gameNm: String? = null
    var marketId: String? = null
    var publishedYn: String? = null
}

internal fun AssetResponse.asDomain() = FncyAsset(
    assetId = assetId,
    chainId = chainId
).also {
    it.assetOrd = assetOrd
    it.displayYn = displayYn
    it.balance = balance
    it.balancePlainString = balancePlainString
    it.displayBalance = displayBalance
    it.price = price?.asDomain()
    it.assetInfo = assetInfo?.asDomain()
    it.gameCode = gameCode
    it.gameNm = gameNm
    it.marketId = marketId
    it.publishedYn = publishedYn
}