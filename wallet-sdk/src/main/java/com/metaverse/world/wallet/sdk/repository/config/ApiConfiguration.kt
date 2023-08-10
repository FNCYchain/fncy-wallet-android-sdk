package com.metaverse.world.wallet.sdk.repository.config

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

internal class ApiConfiguration(
    private val baseUrl: String,
    private val apiKey: String,
) {

    init {
        Timber.w("$this created.")
    }

    val endPoint: String
        get() = baseUrl

    val headerInterceptor: Interceptor
        get() = Interceptor { chain ->
            val request: Request = chain.request()
            val requestBuilder = request.newBuilder()

            runBlocking {
                val headers = defaultHeader()
                headers.keys
                    .filterNot { request.headers.names().contains(it) }
                    .forEach { key ->
                        headers[key]?.let { value ->
                            requestBuilder.addHeader(key, value)
                        }
                    }
            }

            chain.proceed(requestBuilder.build())
        }

    val logInterceptor: Interceptor
        get() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

     val connectTimeout: Long
        get() = 60L
     val writeTimeout: Long
        get() = 60L
     val readTimeout: Long
        get() = 60L
     val defaultHeader: suspend () -> Map<String, String> = {
        HashMap<String, String>().apply {
//            put("user-agent", userAgent)
            put("Api-Key", apiKey)
//            put("App-Session-Guid", deviceConfiguration.sessionGUID)
//            put("App-Device-Udid", deviceConfiguration.deviceUDID)
//            put("device-model", deviceConfiguration.deviceModel)
//            put("App-Version", appConfiguration.appVersion)
//            put("App-Language", appSetting?.languageCode ?: "")
//            put("App-Device-Currency", appSetting?.currencySymbol ?: "")
        }
    }

//    private val userAgent: String
//        get() {
//            val sb = StringBuilder()
//            sb.append("${appConfiguration.appName}/")
//                .append(appConfiguration.appVersion)
//                .append(" (")
//                .append("Linux; ")
//                .append("Android ")
//                .append(deviceConfiguration.osVersion)
//                .append("; ")
//                .append(deviceConfiguration.device)
//                .append(" Build/")
//                .append(deviceConfiguration.deviceModel)
//                .append("); ")
//            return sb.toString()
//        }
}




