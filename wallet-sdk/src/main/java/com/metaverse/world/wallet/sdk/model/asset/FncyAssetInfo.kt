package com.metaverse.world.wallet.sdk.model.asset

@kotlinx.serialization.Serializable
data class FncyAssetInfo(
    val assetId: Long
) {
    var assetTypeDcd: String? = null
    var assetType: String? = null
    var contractAddress: String? = null
    var assetNm: String? = null
    var assetSymbol: String? = null
    var assetSymbolImg: String? = null
    var assetDecimal: Long = 0
    var gcoinYn: String? = null
    var assetDesc: String? = null
    var cubeYn: String? = null
    var defaultAssetYn: String? = null
    var chainId: Long = 0
    var chainNm: String? = null
    var assetButtonTypeDcd: String? = null
    var assetButtonType: String? = null
}