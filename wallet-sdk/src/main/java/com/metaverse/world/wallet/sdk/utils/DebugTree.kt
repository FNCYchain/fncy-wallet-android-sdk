package com.metaverse.world.wallet.sdk.utils

import timber.log.Timber

internal class DebugTree: Timber.DebugTree() {

    override fun log(priority: Int, message: String?, vararg args: Any?) {
        super.log(priority, getLogMessage(message), *args)
    }

    override fun log(priority: Int, t: Throwable?, message: String?, vararg args: Any?) {
        super.log(priority, t, getLogMessage(message), *args)
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, "FncyWallet", getLogMessage(message), t)
    }

    private fun getLogMessage(message: String?): String {
        val ste = Thread.currentThread().stackTrace[7]
        return String.format(
                    "[%s::%s](%s) %s",
                    ste.fileName,
                    ste.methodName,
                    Thread.currentThread().name,
                    message
        )
    }
}