package com.metaverse.world.wallet.sdk.crypto

import com.google.android.gms.common.util.Hex


internal class EncryptDataManager(
    private val fncyCryptoManager: FncyCryptoManager
) {
    private fun encryptDataWithRsa(plainData: String, rsaKey: String)=
        fncyCryptoManager.encryptWithPublicKey(plainData, rsaKey)

    private fun getHashData(plainData: String, count: Int) =
        Hex.bytesToStringLowercase(fncyCryptoManager.getDuplicatedSha256(plainData, count))

    fun encryptData(hash: String, rsaKey: String) =
        encryptDataWithRsa(hash, rsaKey)

    fun encrypt(plainData: String, rsaKey: String, count: Int) =
        encryptDataWithRsa(getHashData(plainData, count), rsaKey)

}