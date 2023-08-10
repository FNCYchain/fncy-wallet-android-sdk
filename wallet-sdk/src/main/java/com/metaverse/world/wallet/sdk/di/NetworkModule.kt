package com.metaverse.world.wallet.sdk.di

import com.metaverse.world.wallet.sdk.repository.network.retrofit.RetrofitGenerator
import com.metaverse.world.wallet.sdk.repository.network.parser.ApiResultParser
import com.metaverse.world.wallet.sdk.repository.network.parser.FncyWalletApiResultParser
import org.koin.dsl.module

internal fun networkModule() = module {
    single {
        RetrofitGenerator(
            apiConfiguration = get()
        ).retrofit
    }

    single<ApiResultParser> {
        FncyWalletApiResultParser()
    }
}