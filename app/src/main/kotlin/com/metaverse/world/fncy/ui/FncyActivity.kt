package com.metaverse.world.fncy.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import com.metaverse.world.wallet.sdk.FncyWalletSDK
import timber.log.Timber

class FncyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                LaunchedEffect(Unit) {
                    val fncyWalletSDK = FncyWalletSDK("")
                    val wallet = fncyWalletSDK.getWallet()
                    wallet.onSuccess {
                        Timber.d(it.toString())
                    }.onFailure {
                        Timber.e(it)
                    }
                }

            }
        }
    }

    suspend fun testWallet() {
        val fncyWalletSDK = FncyWalletSDK("")
        val wallet = fncyWalletSDK.getWallet()
        wallet.onSuccess {
            Timber.d(it.toString())
        }.onFailure {
            Timber.e(it)
        }
    }
}