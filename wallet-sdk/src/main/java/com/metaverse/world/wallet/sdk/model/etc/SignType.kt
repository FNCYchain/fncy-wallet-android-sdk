package com.metaverse.world.wallet.sdk.model.etc

enum class SignType(
    val value: String
) {
    EthSign("ethSign"),
    EthSignV2("ethSignV2"),
    PersonalSign("ethSignPersonalWithPrefix"),
    WithoutPrefix("ethSignWithoutPrefix"),
    Eip712Sign("signEip712StructuredData")
}