package com.metaverse.world.wallet.sdk.model.nft

import com.metaverse.world.wallet.sdk.repository.network.serialization.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class FncyNFTAttribute(
    val attrSeq: Long,
) {
    val nftSeq: Long? = null
    val trait: String? = null
    val traitValue: String? = null
    @Serializable(with = BigDecimalSerializer::class)
    val rarity: BigDecimal = BigDecimal.ZERO
    val order: Long? = null
    val createDt: Long? = null
    val updateUts: Long? = null
}
