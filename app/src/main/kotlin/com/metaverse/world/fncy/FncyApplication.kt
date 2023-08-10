package com.metaverse.world.fncy

import android.app.Application
import com.metaverse.world.wallet.sdk.FncyWalletSDK

class FncyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FncyWalletSDK.initSDK(
            application = this@FncyApplication,
        )
    }
}