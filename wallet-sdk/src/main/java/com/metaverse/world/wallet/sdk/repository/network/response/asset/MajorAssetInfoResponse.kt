package com.metaverse.world.wallet.sdk.repository.network.response.asset

import com.metaverse.world.wallet.sdk.model.asset.FncyMajorAssetInfo

@kotlinx.serialization.Serializable
internal data class MajorAssetInfoResponse(
    val majorAssetId: Long = 0,
    val majorAssetSymbol: String? = null,
    val majorAssetSymbolImg: String? = null,
    val totalBalance: String? = null,
    val displayTotalBalance: String? = null,
    val totalUsdPrice: String? = null,
    val displayTotalUsdPrice: String? = null,
    val totalKrwPrice: String? = null,
    val displayTotalKrwPrice: String? = null,
    val contractAddress: String? = null,
)

internal fun MajorAssetInfoResponse.asDomain() = FncyMajorAssetInfo(
    majorAssetId = majorAssetId,
    majorAssetSymbol = majorAssetSymbol,
    majorAssetSymbolImg = majorAssetSymbolImg,
    contractAddress = contractAddress,
)