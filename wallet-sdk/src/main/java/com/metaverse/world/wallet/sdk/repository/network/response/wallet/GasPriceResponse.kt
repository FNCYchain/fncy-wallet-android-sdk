package com.metaverse.world.wallet.sdk.repository.network.response.wallet

import com.metaverse.world.wallet.sdk.model.wallet.FncyGasPrice
import com.metaverse.world.wallet.sdk.repository.network.serialization.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
internal data class GasPriceResponse(
    @Serializable(with = BigDecimalSerializer::class)
    val fastGasPrice: BigDecimal = BigDecimal.ZERO,
    @Serializable(with = BigDecimalSerializer::class)
    val middleGasPrice: BigDecimal = BigDecimal.ZERO,
    @Serializable(with = BigDecimalSerializer::class)
    val slowGasPrice: BigDecimal = BigDecimal.ZERO,
    @Serializable(with = BigDecimalSerializer::class)
    val baseFeePerGas: BigDecimal = BigDecimal.ZERO,
    @Serializable(with = BigDecimalSerializer::class)
    val maxPriorityFeePerGas: BigDecimal = BigDecimal.ZERO
)

internal fun GasPriceResponse.asDomain() = FncyGasPrice(
    fastGasPrice = fastGasPrice,
    middleGasPrice = middleGasPrice,
    slowGasPrice = slowGasPrice,
    baseFeePerGas = baseFeePerGas,
    maxPriorityFeePerGas = maxPriorityFeePerGas
)