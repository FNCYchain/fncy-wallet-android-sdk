package com.metaverse.world.wallet.sdk.model.etc

enum class InOut(val value: String?) {
    All(null),
    Deposit("withdrawal"),
    Withdrawal("deposit"),
}