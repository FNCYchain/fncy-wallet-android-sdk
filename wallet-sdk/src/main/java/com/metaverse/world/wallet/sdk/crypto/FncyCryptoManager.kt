package com.metaverse.world.wallet.sdk.crypto

import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

internal class FncyCryptoManager {

    private fun getSha256(msg: ByteArray): ByteArray = runCatching {
        return@runCatching MessageDigest.getInstance("SHA-256").let {
            it.update(msg)
            it.digest()
        }
    }.getOrThrow()

    fun getDuplicatedSha256(msg: String, count: Int): ByteArray = runCatching {
        var key = msg.toByteArray()
        (0 until count).forEach { _ ->
            key = getSha256(key)
        }
        return@runCatching key
    }.getOrThrow()

    fun encryptWithPublicKey(plainText: String, publicKeyBase64: String): String = runCatching {
        val keySpec = X509EncodedKeySpec(Base64.decode(publicKeyBase64, Base64.NO_WRAP))
        val publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec)
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding").apply {
            init(Cipher.ENCRYPT_MODE, publicKey)
        }
        return@runCatching Base64.encodeToString(
            cipher.doFinal(
                plainText.toByteArray(
                    StandardCharsets.UTF_8
                )
            ), Base64.NO_WRAP
        )
    }.getOrThrow()

}