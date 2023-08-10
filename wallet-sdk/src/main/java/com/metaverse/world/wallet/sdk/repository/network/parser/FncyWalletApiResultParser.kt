package com.metaverse.world.wallet.sdk.repository.network.parser

import com.metaverse.world.wallet.sdk.model.error.FncyError
import com.metaverse.world.wallet.sdk.model.error.FncyException
import com.metaverse.world.wallet.sdk.repository.network.response.FncyResponse

internal class FncyWalletApiResultParser: ApiResultParser {

    override fun <T> parse(response: FncyResponse<T>): T {
        val code = 100
        return response.run {
            if (code in 200 until 300) {
                data ?: throw FncyError.Null.exception
            } else throw FncyException(
                code = code,
                errMsg = errMsg
            )
        }
    }
}