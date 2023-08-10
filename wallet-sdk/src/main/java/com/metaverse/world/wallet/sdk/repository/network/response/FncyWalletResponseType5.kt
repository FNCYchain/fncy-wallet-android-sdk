package com.metaverse.world.wallet.sdk.repository.network.response

import kotlinx.serialization.Serializable

// Transaction Ticket Data 획득 전용
@Serializable
internal data class FncyWalletResponseType5<T>(
    val apiVersion: String,
    val status: Status,
    val web3Error: Web3Error? = null,
    override val data: Data5<T>? = null
): FncyResponse<Data5<T>>() {

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
internal data class Data5<T>(val items: T? = null) {
    val result: Result? = null
    val ticketUuid: String? = null
    val ticketHash: String? = null
}

