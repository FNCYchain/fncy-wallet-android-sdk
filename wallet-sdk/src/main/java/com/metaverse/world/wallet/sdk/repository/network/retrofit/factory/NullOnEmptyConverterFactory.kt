package com.metaverse.world.wallet.sdk.repository.network.retrofit.factory

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

internal class NullOnEmptyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val delegate: Converter<ResponseBody, *> =
            retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
        return Converter { body: ResponseBody ->
            if (body.contentLength() == 0L) {
                return@Converter null
            }
            delegate.convert(body)
        }
    }
}