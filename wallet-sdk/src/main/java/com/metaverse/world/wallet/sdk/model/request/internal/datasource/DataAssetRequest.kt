package com.metaverse.world.wallet.sdk.model.request.internal.datasource

import com.metaverse.world.wallet.sdk.model.etc.NFTOption
import com.metaverse.world.wallet.sdk.model.etc.NFTType
import com.metaverse.world.wallet.sdk.repository.data.asset.FncyAssetCategory
import com.metaverse.world.wallet.sdk.repository.network.response.Paging

internal data class ReqAssetsByCategory(
    val wid: Long,
    val fncyAssetCategory: FncyAssetCategory,
    val paging: Paging = Paging()
)

internal data class ReqAssetList(
    val wid: Long,
    val paging: Paging = Paging(),
    val fncyAssetCategory: FncyAssetCategory? = null
)

internal fun ReqAssetList.asByCategory() =
    ReqAssetsByCategory(
        wid = wid,
        fncyAssetCategory = fncyAssetCategory ?: FncyAssetCategory.FNCY,
        paging = paging
    )

internal data class ReqAssetByAssetId(
    val wid: Long,
    val assetId: Long
)

/**
 * @param option: NFT 조회 옵션 DEFAULT: 전체
 * @param nftType: NFT 타입
 */
internal data class ReqNftAssetByOption(
    val wid: Long,
    val option: NFTOption? = null,
    val nftType: NFTType? = null,
    val paging: Paging = Paging()
)

internal data class ReqNftAssetByNftId(
    val wid: Long,
    val nftId: Long
)

internal data class ReqBlockchainInfo(val chainId: Long)

internal data class ReqBlockchainAssetByContractAddress(
    val chainId: Long,
    val contractAddress: String
)