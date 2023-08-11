package com.metaverse.world.wallet.sdk.di

import com.metaverse.world.wallet.sdk.crypto.FncyCryptoManager
import com.metaverse.world.wallet.sdk.crypto.FncyEncryptManager
import com.metaverse.world.wallet.sdk.utils.PinValidator
import org.koin.dsl.module

internal fun cryptoModule() = module {
    single {
        PinValidator()
    }
    single {
        FncyCryptoManager()
    }
    single {
        FncyEncryptManager(get())
    }
}