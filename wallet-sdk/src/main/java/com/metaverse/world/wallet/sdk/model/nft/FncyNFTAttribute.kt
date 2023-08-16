package com.metaverse.world.wallet.sdk.model.nft

import java.math.BigDecimal

data class FncyNFTAttribute(
    val attrSeq: Long,
) {
    var nftSeq: Long? = null
    var trait: String? = null
    var traitValue: String? = null
    var rarity: BigDecimal = BigDecimal.ZERO
    var order: Long? = null
    var createDt: Long? = null
    var updateUts: Long? = null
}
