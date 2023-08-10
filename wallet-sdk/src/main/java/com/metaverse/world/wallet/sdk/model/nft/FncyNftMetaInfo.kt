package com.metaverse.world.wallet.sdk.model.nft


@kotlinx.serialization.Serializable
data class FncyNFTMetaInfo(
    var gameName: String? = null
) {
    var name: String? = null
    var description: String? = null
    var properties: Map<String, String>? = null
    var status: String? = null
}