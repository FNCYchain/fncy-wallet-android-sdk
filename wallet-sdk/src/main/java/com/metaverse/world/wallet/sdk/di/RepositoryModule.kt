package com.metaverse.world.wallet.sdk.di

import com.metaverse.world.wallet.sdk.repository.data.account.FncyAccountRepository
import com.metaverse.world.wallet.sdk.repository.data.account.FncyAccountRepositoryImpl
import com.metaverse.world.wallet.sdk.repository.data.asset.FncyAssetRepository
import com.metaverse.world.wallet.sdk.repository.data.asset.FncyAssetRepositoryImpl
import com.metaverse.world.wallet.sdk.repository.data.transaction.FncyTransactionRepository
import com.metaverse.world.wallet.sdk.repository.data.transaction.FncyTransactionRepositoryImpl
import com.metaverse.world.wallet.sdk.repository.data.wallet.FncyWalletRepository
import com.metaverse.world.wallet.sdk.repository.data.wallet.FncyWalletRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal fun repositoryModule() = module {
    factory<FncyWalletRepository> {
        FncyWalletRepositoryImpl(get(), get(), get(), get(), get(), get(named("IoDispatcher")))
    }

    factory<FncyAccountRepository> {
        FncyAccountRepositoryImpl(get(), get(), get(named("IoDispatcher")))
    }

    factory<FncyAssetRepository> {
        FncyAssetRepositoryImpl(get(), get(), get(named("IoDispatcher")))
    }

    factory<FncyTransactionRepository> {
        FncyTransactionRepositoryImpl(get(), get(), get(), get(), get(), get(named("IoDispatcher")))
    }
}