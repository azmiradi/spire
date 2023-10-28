package com.azmiradi.kotlin_base.helpers

interface ICryptoOperations {
    fun encryptAES(keyAlias: String, textInBytes: ByteArray): ByteArray
    fun decryptAES(keyAlias: String, encryptedDataWithIV: ByteArray): ByteArray
}