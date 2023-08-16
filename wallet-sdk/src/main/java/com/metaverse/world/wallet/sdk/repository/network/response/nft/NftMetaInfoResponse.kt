package com.metaverse.world.wallet.sdk.repository.network.response.nft

import com.metaverse.world.wallet.sdk.model.nft.FncyNFTMetaInfo


@kotlinx.serialization.Serializable
internal data class NFTMetaInfoResponse(
    var gameName: String? = null
) {
    var name: String? = null
    var description: String? = null
    var properties: Map<String, String>? = null
    var status: String? = null
}

internal fun NFTMetaInfoResponse.asDomain() = FncyNFTMetaInfo(
    gameName = gameName,
).also {
    it.name = name
    it.description = description
    it.properties = properties
    it.status = status
}