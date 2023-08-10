package com.metaverse.world.wallet.sdk.repository.network.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal

internal object BigDecimalSerializer : KSerializer<BigDecimal> {

    override fun deserialize(decoder: Decoder): BigDecimal {
        return decoder.decodeString().ifEmpty { "0" } .toBigDecimal()
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        encoder.encodeString(value.toPlainString().ifEmpty { "0" })
    }
}