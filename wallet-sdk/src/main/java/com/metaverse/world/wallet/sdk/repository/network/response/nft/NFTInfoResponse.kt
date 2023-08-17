package com.metaverse.world.wallet.sdk.repository.network.response.nft

import com.metaverse.world.wallet.sdk.model.nft.FncyNFTInfo

@kotlinx.serialization.Serializable
internal data class NFTInfoResponse(
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
    var nftMetaInfo: NFTMetaInfoResponse? = null
    var attributes: List<NFTAttributeResponse>? = null
    var nftMetaJson: String? = null
    var useYn: String? = null
    var nftDesc: String? = null
}

internal fun NFTInfoResponse.asDomain() = FncyNFTInfo(
    chainId = chainId
).also {
    it.assetTypeDcd = assetTypeDcd
    it.assetType = assetType
    it.nftTypeDcd = nftTypeDcd
    it.nftType = nftType
    it.contractAddress = contractAddress
    it.tokenId = tokenId
    it.nftNm = nftNm
    it.nftSymbol = nftSymbol
    it.nftSymbolImg = nftSymbolImg
    it.nftMetaUri = nftMetaUri
    it.nftMediaUri = nftMediaUri
    it.nftAnimationUrl = nftAnimationUrl
    it.nftDirectLink = nftDirectLink
    it.holderAuthYn = holderAuthYn
    it.nftHolderAuthDirectLink = nftHolderAuthDirectLink
    it.marketId = marketId
    it.attributes = attributes?.map { attrs -> attrs.asDomain() }
    it.nftMetaJson = nftMetaJson
    it.nftDesc = nftDesc
//    it.useYn = useYn
//    it.nftMetaInfo = nftMetaInfo?.asDomain()
}