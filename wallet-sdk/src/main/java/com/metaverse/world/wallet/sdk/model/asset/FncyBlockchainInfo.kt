package com.metaverse.world.wallet.sdk.model.asset

@kotlinx.serialization.Serializable
class FncyBlockchainInfo (
    val chainId: Long = 0,
    val createKst: Long = 0,
    val updateKst: Long = 0,
    val chainTypeDcd: String? = null,
    val chainType: String? = null,
    val chainNm: String? = null,
    val testnetYn: String? = null,
    val chainActiveYn: String? = null,
    val chainDesc: String? = null,
    val fncyNativeCoinInfo: FncyNativeCoinInfo? = null,
    val fncyMajorAssetInfo: FncyMajorAssetInfo? = null
)
