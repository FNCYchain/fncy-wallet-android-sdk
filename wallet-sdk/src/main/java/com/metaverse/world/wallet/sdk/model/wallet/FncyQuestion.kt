package com.metaverse.world.wallet.sdk.model.wallet

@kotlinx.serialization.Serializable
data class FncyQuestion(
    val questionSeq: Long,
    val questionKr: String? = null,
    val questionEn: String? = null
)