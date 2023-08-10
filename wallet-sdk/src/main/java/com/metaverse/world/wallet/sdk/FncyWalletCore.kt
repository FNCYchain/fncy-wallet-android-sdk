package com.metaverse.world.wallet.sdk

import android.content.Context
import com.metaverse.world.wallet.sdk.di.apiConfigModule
import com.metaverse.world.wallet.sdk.di.coroutineModule
import com.metaverse.world.wallet.sdk.di.cryptoModule
import com.metaverse.world.wallet.sdk.di.dataSourceModule
import com.metaverse.world.wallet.sdk.di.networkModule
import com.metaverse.world.wallet.sdk.di.repositoryModule
import com.metaverse.world.wallet.sdk.repository.data.account.FncyAccountRepository
import com.metaverse.world.wallet.sdk.repository.data.asset.FncyAssetRepository
import com.metaverse.world.wallet.sdk.repository.data.transaction.FncyTransactionRepository
import com.metaverse.world.wallet.sdk.repository.data.wallet.FncyWalletRepository
import com.metaverse.world.wallet.sdk.utils.DebugTree
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import timber.log.Timber

internal object FncyWalletCore { // Singleton

    private val koinApp: KoinApplication = KoinApplication.init().apply { createEagerInstances() }

    internal val walletRepository: FncyWalletRepository by lazy {
        koinApp.koin.get()
    }

    internal val assetRepository: FncyAssetRepository by lazy {
        koinApp.koin.get()
    }

    internal val accountRepository: FncyAccountRepository by lazy {
        koinApp.koin.get()
    }

    internal val transactionRepository: FncyTransactionRepository by lazy {
        koinApp.koin.get()
    }

    /**
     * Initialize FncyCore
     * @param application
     */
    fun initialize(
        application: Context,
    ) {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        with(koinApp) {
            androidContext(application)
            modules(
                apiConfigModule(),
                coroutineModule(),
                networkModule(),
                cryptoModule(),
                dataSourceModule(),
                repositoryModule(),
            )
        }
    }

}
