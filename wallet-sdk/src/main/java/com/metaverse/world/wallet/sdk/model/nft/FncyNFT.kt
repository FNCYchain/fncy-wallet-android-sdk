package com.metaverse.world.wallet.sdk.model.nft

data class FncyNFT(
    val nftId: Long
) {
    var wid: Long = 0
    var ownerOfDcd: String? = null
    var ownerOf: String? = null
    var lockedYn: String? = null
    var nftOrd: Long = 0
    var displayYn: String? = null
    var nftInfo: FncyNFTInfo? = null
}