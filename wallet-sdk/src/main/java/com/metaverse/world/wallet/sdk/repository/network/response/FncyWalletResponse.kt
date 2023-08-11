package com.metaverse.world.wallet.sdk.repository.network.response

import com.metaverse.world.wallet.sdk.model.error.FncyError
import kotlinx.serialization.Serializable

@Serializable
internal data class FncyWalletResponse<T>(
    val apiVersion: String,
    val status: Status,
    override val data: Data<T>? = null
): FncyResponse<Data<T>>() {

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
internal data class Status(val code: Int, val message: String, val error: ErrorStatus? = null)
@Serializable
internal data class ErrorStatus(val code: Int, val type: String, val message: String)

@Serializable
data class Paging(
    val pageNo: Int = 1,
    val pageSize: Int = 20,
    val totalCount: Int = 0,
    val hasMore: Boolean = false
)

internal fun Paging.toParamMap() =
    mapOf(
        "pageNo" to pageNo.toString(),
        "pageSize" to pageSize.toString(),
        "totalCount" to totalCount.toString(),
        "hasMore" to hasMore.toString(),
    )

@Serializable
internal data class Result(
    val isSuccess: Boolean,
    val code: String,
    val number: Int,
    val message: String
)

@Serializable
internal data class Data<T>(val items: T? = null, val result: Result? = null, val paging: Paging? = null)

internal fun <T> Data<T>.successData(): T = items ?: run {
    throw FncyError.Null.exception
}

internal fun <T> Data<T>.toPagingResponse(): PagingData<T?> = PagingData(
    items,
    requireNotNull(paging)
)

internal fun <T> Data<T>.isSuccess() = result?.isSuccess == true