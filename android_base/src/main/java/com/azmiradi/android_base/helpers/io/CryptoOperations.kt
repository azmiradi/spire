package com.azmiradi.android_base.helpers.io

import com.azmiradi.kotlin_base.helpers.ICryptoOperations


class CryptoOperations : ICryptoOperations {
    override fun encryptAES(keyAlias: String, textInBytes: ByteArray): ByteArray {
//        return KeyStoreCryptoOperations.encryptAES(keyAlias, textInBytes)
        TODO()
    }

    override fun decryptAES(keyAlias: String, encryptedDataWithIV: ByteArray): ByteArray {
//        return KeyStoreCryptoOperations.decryptAES(keyAlias, encryptedDataWithIV)
        TODO()
    }
}