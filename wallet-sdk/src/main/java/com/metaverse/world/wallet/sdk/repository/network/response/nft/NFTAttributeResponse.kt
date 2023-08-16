package com.metaverse.world.wallet.sdk.repository.network.response.nft

import com.metaverse.world.wallet.sdk.model.nft.FncyNFTAttribute
import com.metaverse.world.wallet.sdk.repository.network.serialization.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
internal data class NFTAttributeResponse(
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

internal fun NFTAttributeResponse.asDomain() = FncyNFTAttribute(
    attrSeq = attrSeq,
).also {
    it.nftSeq = nftSeq
    it.trait = trait
    it.traitValue = traitValue
    it.rarity = rarity
    it.order = order
    it.createDt = createDt
    it.updateUts = updateUts
}