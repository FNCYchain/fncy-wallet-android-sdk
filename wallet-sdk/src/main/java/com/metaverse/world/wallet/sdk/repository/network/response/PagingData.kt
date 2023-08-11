package com.metaverse.world.wallet.sdk.repository.network.response

data class PagingData<T>(
    val data: T,
    val paging: Paging
)
