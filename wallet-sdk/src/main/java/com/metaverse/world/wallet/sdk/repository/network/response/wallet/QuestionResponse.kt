package com.metaverse.world.wallet.sdk.repository.network.response.wallet

@kotlinx.serialization.Serializable
internal data class QuestionResponse(
    val questionSeq: Long,
    val questionKr: String? = null,
    val questionEn: String? = null
)

internal fun QuestionResponse.asDomain() = com.metaverse.world.wallet.sdk.model.wallet.FncyQuestion(
    questionSeq = questionSeq,
    questionKr = questionKr,
    questionEn = questionEn
)