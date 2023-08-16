package com.metaverse.world.wallet.sdk.model.wallet

import java.math.BigDecimal

data class FncyGasPrice(
    val fastGasPrice: BigDecimal = BigDecimal.ZERO,
    val middleGasPrice: BigDecimal = BigDecimal.ZERO,
    val slowGasPrice: BigDecimal = BigDecimal.ZERO,
    val baseFeePerGas: BigDecimal = BigDecimal.ZERO,
    val maxPriorityFeePerGas: BigDecimal = BigDecimal.ZERO
)