package com.metaverse.world.wallet.sdk.repository.network.response.asset

import com.metaverse.world.wallet.sdk.model.asset.FncyAssetInfo

@kotlinx.serialization.Serializable
internal data class AssetInfoResponse(
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

internal fun AssetInfoResponse.asDomain() = FncyAssetInfo(
    assetId = assetId
).also {
    it.assetTypeDcd = assetTypeDcd
    it.assetType = assetType
    it.contractAddress = contractAddress
    it.assetNm = assetNm
    it.assetSymbol = assetSymbol
    it.assetSymbolImg = assetSymbolImg
    it.assetDecimal = assetDecimal
    it.assetDesc = assetDesc
    it.chainId = chainId
    it.chainNm = chainNm
}
