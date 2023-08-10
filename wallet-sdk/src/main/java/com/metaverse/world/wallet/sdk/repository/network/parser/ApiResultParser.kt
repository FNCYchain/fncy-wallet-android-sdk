package com.metaverse.world.wallet.sdk.repository.network.parser

import com.metaverse.world.wallet.sdk.repository.network.response.FncyResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal interface ApiResultParser {
    fun <T> parse(response: FncyResponse<T>): T
}


@ExperimentalSerializationApi
inline fun <reified T> decodeFromStringWithConfig(string: String): T {
    val json = Json {
        isLenient = true // Json 큰따옴표 느슨하게 체크.
        ignoreUnknownKeys = true // Field 값이 없는 경우 무시
        coerceInputValues = true // "null" 이 들어간경우 default Argument 값으로 대체}
    }

    return json.decodeFromString(string)
}