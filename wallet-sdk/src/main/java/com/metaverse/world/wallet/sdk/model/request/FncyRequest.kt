package com.metaverse.world.wallet.sdk.model.request

internal data class FncyRequest<T>(
    val accessToken: String,
    val params: T
)
