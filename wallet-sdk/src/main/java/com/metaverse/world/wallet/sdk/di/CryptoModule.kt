package com.metaverse.world.wallet.sdk.di

import com.metaverse.world.wallet.sdk.crypto.EncryptDataManager
import com.metaverse.world.wallet.sdk.crypto.FncyCryptoManager
import org.koin.dsl.module

internal fun cryptoModule() = module {
    single {
        FncyCryptoManager()
    }
    single {
        EncryptDataManager(get())
    }
}