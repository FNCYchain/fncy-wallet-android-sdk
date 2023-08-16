package com.metaverse.world.wallet.sdk.repository.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.metaverse.world.wallet.sdk.repository.config.ApiConfiguration
import com.metaverse.world.wallet.sdk.repository.network.retrofit.factory.NullOnEmptyConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalSerializationApi::class)
internal class RetrofitGenerator(
    private val apiConfiguration: ApiConfiguration
) {

    val retrofit: Retrofit
        get() = retrofitInit()

    private fun retrofitInit(): Retrofit =
        runBlocking {
            val okHttpClient = apiConfiguration.logInterceptor?.let {
                OkHttpClient()  // should be singleton
                    .newBuilder()
                    .connectTimeout(apiConfiguration.connectTimeout, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
                    .writeTimeout(apiConfiguration.writeTimeout, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
                    .readTimeout(apiConfiguration.readTimeout, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
                    .addInterceptor(apiConfiguration.headerInterceptor) // header 삽입
                    .addInterceptor(it) // log interceptor 추가
                    .build()
            } ?: OkHttpClient()  // should be singleton
                .newBuilder()
                .connectTimeout(apiConfiguration.connectTimeout, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
                .writeTimeout(apiConfiguration.writeTimeout, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
                .readTimeout(apiConfiguration.readTimeout, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
                .addInterceptor(apiConfiguration.headerInterceptor) // header 삽입
                .build()
//            apiConfiguration.logInterceptor?.let {
//                okHttpClient.newBuilder().addInterceptor(it).build()
//            }
            Retrofit.Builder()
                .baseUrl(apiConfiguration.endPoint)
                .client(
                    okHttpClient
                ).addConverterFactory(NullOnEmptyConverterFactory()) //Json Parser 추가
                .addConverterFactory(
                    json.asConverterFactory("application/json".toMediaType())
                ).build()
        }

    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys  = true
        isLenient = true
        allowStructuredMapKeys  = true
        prettyPrint = true
        coerceInputValues = true
        useArrayPolymorphism  = true
        allowSpecialFloatingPointValues  = true
        useAlternativeNames = true
    }

}