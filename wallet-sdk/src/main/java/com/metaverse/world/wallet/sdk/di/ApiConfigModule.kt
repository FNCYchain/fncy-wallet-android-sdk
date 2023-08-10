package com.metaverse.world.wallet.sdk.di

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.metaverse.world.wallet.sdk.repository.config.ApiConfiguration
import com.metaverse.world.wallet.sdk.utils.STRING_KEY_API
import com.metaverse.world.wallet.sdk.utils.STRING_KEY_BASE_URL
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal fun apiConfigModule() = module {
    single {
        val context = androidContext()
        ApiConfiguration(
            baseUrl = getMetaData(context, STRING_KEY_BASE_URL),
            apiKey = getMetaData(context, STRING_KEY_API),
        )
    }
}

private fun getMetaData(context: Context, key: String) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    context.packageManager.getApplicationInfo(
        context.packageName,
        PackageManager.ApplicationInfoFlags.of(PackageManager.GET_META_DATA.toLong())
    ).metaData?.let {
        it.getString(key) ?: ""
    } ?: ""
} else {
    context.packageManager.getApplicationInfo(
        context.packageName,
        PackageManager.GET_META_DATA
    ).metaData?.let {
        it.getString(key) ?: ""
    } ?: ""
}
