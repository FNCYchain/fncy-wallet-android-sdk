package com.metaverse.world.wallet.sdk.di

import com.metaverse.world.wallet.sdk.repository.data.account.FncyAccountDataSource
import com.metaverse.world.wallet.sdk.repository.data.account.FncyAccountDataSourceImpl
import com.metaverse.world.wallet.sdk.repository.data.asset.FncyAssetDataSource
import com.metaverse.world.wallet.sdk.repository.data.asset.FncyAssetDataSourceImpl
import com.metaverse.world.wallet.sdk.repository.data.transaction.FncyTransactionDataSource
import com.metaverse.world.wallet.sdk.repository.data.transaction.FncyTransactionDataSourceImpl
import com.metaverse.world.wallet.sdk.repository.data.wallet.FncyWalletDataSource
import com.metaverse.world.wallet.sdk.repository.data.wallet.FncyWalletDataSourceImpl
import org.koin.dsl.module

internal fun dataSourceModule() = module {
    factory<FncyAccountDataSource> {
        FncyAccountDataSourceImpl(get())
    }

    factory<FncyAssetDataSource> {
        FncyAssetDataSourceImpl(get())
    }

    factory<FncyWalletDataSource> {
        FncyWalletDataSourceImpl(get())
    }

    factory<FncyTransactionDataSource> {
        FncyTransactionDataSourceImpl(get())
    }
}