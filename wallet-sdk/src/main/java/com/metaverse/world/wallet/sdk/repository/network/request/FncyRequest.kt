package com.metaverse.world.wallet.sdk.repository.network.request

internal data class FncyRequest<T>(
    val accessToken: String,
    val params: T
)
