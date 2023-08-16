package com.metaverse.world.wallet.sdk.model.asset

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
    var assetDesc: String? = null
    var chainId: Long = 0
    var chainNm: String? = null
}