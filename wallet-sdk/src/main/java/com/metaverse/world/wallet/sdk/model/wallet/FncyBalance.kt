package com.metaverse.world.wallet.sdk.model.wallet

import kotlinx.serialization.Serializable

@Serializable
data class FncyBalance(
    val wid: Long
) {
    var walletNm: String? = null
    var totalUsdPrice: String? = null
    var displayTotalUsdPrice: String? = null
    var totalKrwPrice: String? = null
    var displayTotalKrwPrice: String? = null
    var walletTypeDcd: String? = null
    var walletType: String? = null
    var externalServiceProviderDcd: String? = null
    var externalServiceProvider: String? = null
    var totalCubeBalance: String? = null
    var displayTotalCubeBalance: String? = null
    var nftCount: String? = null
}

