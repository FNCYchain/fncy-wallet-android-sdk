package com.metaverse.world.wallet.sdk.repository.network.response.wallet

import com.metaverse.world.wallet.sdk.model.wallet.FncyBalance
import kotlinx.serialization.Serializable

@Serializable
internal data class BalanceResponse(
    val wid: Long
) {
    var totalUsdPrice: String? = null
    var displayTotalUsdPrice: String? = null
    var totalKrwPrice: String? = null
    var displayTotalKrwPrice: String? = null
    var walletNm: String? = null
    var walletTypeDcd: String? = null
    var walletType: String? = null
    var externalServiceProviderDcd: String? = null
    var externalServiceProvider: String? = null
    var totalCubeBalance: String? = null
    var displayTotalCubeBalance: String? = null
    var nftCount: String? = null
}

internal fun BalanceResponse.asDomain() = FncyBalance(
    wid = wid
).also {
    it.totalUsdPrice = totalUsdPrice
    it.displayTotalUsdPrice = displayTotalUsdPrice
    it.totalKrwPrice = totalKrwPrice
    it.displayTotalKrwPrice = displayTotalKrwPrice
}

