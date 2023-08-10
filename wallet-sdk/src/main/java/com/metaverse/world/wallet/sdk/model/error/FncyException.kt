package com.metaverse.world.wallet.sdk.model.error

internal class FncyException(
    private val code: Int = 0,
    private val errMsg: String,
    private val tag: String = ""
): Throwable() {
    override val message: String
        get() = "code: $code, errMsg: $errMsg"
}

internal enum class FncyError(val exception: FncyException, val tag: String = "") {
    Null(FncyException(errMsg = "Data is Null")),
    Network(FncyException(errMsg = "Network Error")),
}