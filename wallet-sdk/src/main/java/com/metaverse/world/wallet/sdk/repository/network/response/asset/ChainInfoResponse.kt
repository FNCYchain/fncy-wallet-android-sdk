package com.metaverse.world.wallet.sdk.repository.network.response.asset

import com.metaverse.world.wallet.sdk.model.asset.FncyChainInfo

@kotlinx.serialization.Serializable
internal class ChainInfoResponse (
    val chainId: Long = 0,
    val createKst: Long = 0,
    val updateKst: Long = 0,
    val chainTypeDcd: String? = null,
    val chainType: String? = null,
    val chainNm: String? = null,
    val testnetYn: String? = null,
    val chainActiveYn: String? = null,
    val chainDesc: String? = null,
    val nativeCoinInfo: NativeCoinInfoResponse? = null,
    val majorAssetInfo: MajorAssetInfoResponse? = null
)

internal fun ChainInfoResponse.asDomain() = FncyChainInfo(
    chainId = chainId,
    createKst = createKst,
    updateKst = updateKst,
    chainTypeDcd = chainTypeDcd,
    chainType = chainType,
    chainNm = chainNm,
    testnetYn = testnetYn,
    chainActiveYn = chainActiveYn,
    chainDesc = chainDesc,
    nativeCoinInfo = nativeCoinInfo?.asDomain(),
    majorAssetInfo = majorAssetInfo?.asDomain()
)