package com.metaverse.world.wallet.sdk.repository.network.response

data class ResponseWithPaging<T>(
    val response: T,
    val paging: Paging
)
