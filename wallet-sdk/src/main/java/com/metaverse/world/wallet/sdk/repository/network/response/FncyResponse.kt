package com.metaverse.world.wallet.sdk.repository.network.response

import kotlinx.serialization.Serializable

@Serializable
internal abstract class FncyResponse<T> {

    abstract val code: Int
    abstract val errMsg: String
    abstract val errorData: String?
    abstract val data: T?

}
