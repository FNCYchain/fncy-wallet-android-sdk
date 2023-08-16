package com.metaverse.world.wallet.sdk.repository.network.response.nft

@kotlinx.serialization.Serializable
internal data class NFTResponse(
    val nftId: Long
) {
    var wid: Long = 0
    var ownerOfDcd: String? = null
    var ownerOf: String? = null
    var lockedYn: String? = null
    var nftOrd: Long = 0
    var displayYn: String? = null
    var nftInfo: NFTInfoResponse? = null
}

internal fun NFTResponse.asDomain() = com.metaverse.world.wallet.sdk.model.nft.FncyNFT(
    nftId = nftId,
).also {
    it.wid = wid
    it.ownerOfDcd = ownerOfDcd
    it.ownerOf = ownerOf
    it.lockedYn = lockedYn
    it.nftOrd = nftOrd
    it.displayYn = displayYn
    it.nftInfo = nftInfo?.asDomain()
}