package com.metaverse.world.wallet.sdk.repository.network.response

import kotlinx.serialization.Serializable

@Serializable
internal data class FncyWalletResponseType2<T>(
    val apiVersion: String,
    val status: Status,
    override val data: T? = null
): FncyResponse<T>() {

    override val code: Int
        get() {
            return status.code
        }
    override val errMsg: String
        get() {
            return status.message
        }
    override val errorData: String
        get() {
            return status.message
        }

}