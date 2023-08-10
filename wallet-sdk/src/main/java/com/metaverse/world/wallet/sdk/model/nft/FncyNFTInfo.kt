package com.metaverse.world.wallet.sdk.model.nft

@kotlinx.serialization.Serializable
data class FncyNFTInfo(
    val chainId: Long
) {
    var assetTypeDcd: String? = null
    var assetType: String? = null
    var nftTypeDcd: String? = null
    var nftType: String? = null
    var contractAddress: String? = null
    var tokenId: String? = null
    var nftNm: String? = null
    var nftSymbol: String? = null
    var nftSymbolImg: String? = null
    var nftMetaUri: String? = null
    var nftMediaUri: String? = null
    var nftAnimationUrl: String? = null
    var nftDirectLink: String? = null
    var holderAuthYn: String? = null
    var nftHolderAuthDirectLink: String? = null
    var marketId: String? = null
    var nftMetaInfo: FncyNFTMetaInfo? = null
    var attributes: List<FncyNFTAttribute>? = null
    var nftMetaJson: String? = null
    var useYn: String? = null
    var nftDesc: String? = null
    var btnSendtogameYn: String? = null
    var btnNftsellYn: String? = null
    var btnNftcancelYn: String? = null
    var btnNftmodifyYn: String? = null
    var binanceSaleYn: String? = null
}