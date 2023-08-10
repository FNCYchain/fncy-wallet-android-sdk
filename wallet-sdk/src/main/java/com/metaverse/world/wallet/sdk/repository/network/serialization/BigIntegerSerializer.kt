package com.metaverse.world.wallet.sdk.repository.network.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigInteger

internal object BigIntegerSerializer : KSerializer<BigInteger> {

    override fun deserialize(decoder: Decoder): BigInteger {
        return decoder.decodeString().ifEmpty { "0" } .toBigInteger()
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("BigInteger", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: BigInteger) {
        encoder.encodeString(value.toString().ifEmpty { "0" })
    }
}