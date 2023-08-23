package com.metaverse.world.wallet.sdk.di

import com.metaverse.world.wallet.sdk.model.common.Environment
import com.metaverse.world.wallet.sdk.repository.config.ApiConfiguration
import com.metaverse.world.wallet.sdk.utils.FncyMainnet
import com.metaverse.world.wallet.sdk.utils.FncyTestnet
import org.koin.dsl.module

internal fun apiConfigModule(environment: Environment) = module {
    single {
        ApiConfiguration(
            baseUrl = environment.toBaseUrl()
        )
    }
}

private fun Environment.toBaseUrl() = when (this) {
    Environment.Testnet -> FncyTestnet
    Environment.Mainnet -> FncyMainnet
}