package com.metaverse.world.wallet.sdk.repository.network.response

import kotlinx.serialization.Serializable

// Transaction Transfer 에러 핸들링 전용
@Serializable
internal data class FncyWalletResponseType4<T>(
    val apiVersion: String,
    val status: Status,
    override val data: Data4<T>? = null
): FncyResponse<Data4<T>>() {

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

@Serializable
data class Web3Error(
    val code: Long,
    val message: String,
    val data: String? = null
)


@Serializable
internal data class Data4<T>(val items: T? = null) {
    val result: Result? = null
    val web3Error: Web3Error? = null
}
