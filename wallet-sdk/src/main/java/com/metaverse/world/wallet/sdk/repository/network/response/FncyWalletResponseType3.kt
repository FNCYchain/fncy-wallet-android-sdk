package com.metaverse.world.wallet.sdk.repository.network.response

import kotlinx.serialization.Serializable

// Pin Check 히스토리 카운트 전용
@Serializable
internal data class FncyWalletResponseType3<T>(
    val apiVersion: String,
    val status: Status,
    override val data: Data3<T>? = null
): FncyResponse<Data3<T>>() {

    override val code: Int
        get() {
            return data?.let { data ->
                data.result?.number ?: status.code
            }?: status.code
        }
    override val errMsg: String
        get() {
            return data?.let { data ->
                data.result?.message ?: status.message
            }?: status.message
        }
    override val errorData: String
        get() {
            return data?.let { data ->
                data.result?.code ?: status.message
            }?: status.message
        }

}

@Serializable
internal data class Data3<T>(val items: T? = null) {
    val paging: Paging? = null
    val result: Result? = null
    val wrongCount: Int = 0
    val lastWrongKst: Long? = null
}