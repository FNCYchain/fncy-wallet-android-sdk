package com.metaverse.world.wallet.sdk.model.wallet

import com.metaverse.world.wallet.sdk.repository.network.serialization.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class FncyWallet(
    val wid: Long,
    val fid: Long,
    val chainId: Int,
    val walletAddress: String,
    val walletTypeDcd: String,
    val secureLevel: Int,
    val createKst: Long = 0
) {
    var updateKst: Long = 0
    var userSeed: String? = null
    var walletType: String? = null
    var externalServiceProviderDcd: String? = null
    var externalServiceProvider: String? = null

    var walletIndex = 0
    var walletNm: String = ""
    var displayOrd = 0
    var displayYn: String? = null
    var bookmarkYn: String? = null
    var createUts: Long = 0
    var updateUts: Long = 0

    @Serializable(with = BigDecimalSerializer::class)
    var totalUsdPrice: BigDecimal = BigDecimal.ZERO
    var displayTotalUsdPrice: String? = null
    @Serializable(with = BigDecimalSerializer::class)
    var totalKrwPrice: BigDecimal = BigDecimal.ZERO
    var displayTotalKrwPrice: String? = null
    var useGame = false
}