package com.metaverse.world.wallet.sdk

import com.metaverse.world.wallet.sdk.di.apiConfigModule
import com.metaverse.world.wallet.sdk.di.coroutineModule
import com.metaverse.world.wallet.sdk.di.cryptoModule
import com.metaverse.world.wallet.sdk.di.dataSourceModule
import com.metaverse.world.wallet.sdk.di.networkModule
import com.metaverse.world.wallet.sdk.di.repositoryModule
import com.metaverse.world.wallet.sdk.model.common.Environment
import com.metaverse.world.wallet.sdk.repository.data.account.FncyAccountRepository
import com.metaverse.world.wallet.sdk.repository.data.asset.FncyAssetRepository
import com.metaverse.world.wallet.sdk.repository.data.transaction.FncyTransactionRepository
import com.metaverse.world.wallet.sdk.repository.data.wallet.FncyWalletRepository
import com.metaverse.world.wallet.sdk.utils.DebugTree
import org.koin.core.KoinApplication
import timber.log.Timber

internal object FncyWalletCore { // Singleton

    internal val koinApp: KoinApplication = KoinApplication.init().apply { createEagerInstances() }

    /**
     * Initialize FncyCore
     * @param environment
     */
    fun initialize(
        environment: Environment
    ) {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        Timber.d("$this created.")
        with(koinApp) {
            modules(
                apiConfigModule(environment),
                coroutineModule(),
                networkModule(),
                cryptoModule(),
                dataSourceModule(),
                repositoryModule(),
            )
        }
    }

}
