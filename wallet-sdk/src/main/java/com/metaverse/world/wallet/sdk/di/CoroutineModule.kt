package com.metaverse.world.wallet.sdk.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal fun coroutineModule() = module {
    single(named("IoDispatcher")) {
        Dispatchers.IO
    }

    single(named("testScope")) {
        CoroutineScope(get(named("IoDispatcher")))
    }
}