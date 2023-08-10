package com.metaverse.world.wallet.sdk.utils

import java.util.regex.Pattern

class PinValidator {

    private val pinPattern by lazy {
        Pattern.compile("^[0-9]{6}$")
    }
    fun check(pin: String): Boolean {
        return pinPattern.matcher(pin).matches()
    }
}