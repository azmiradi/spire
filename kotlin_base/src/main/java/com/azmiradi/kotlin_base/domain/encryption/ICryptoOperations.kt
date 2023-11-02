package com.azmiradi.kotlin_base.domain.encryption

interface ICryptoOperations {
    fun encryptDataWitRSA(keyAlias: String, data: ByteArray): ByteArray
    fun decryptDataWithRSA(keyAlias: String, data: ByteArray): ByteArray
    fun encryptDataWithAES(keyAlias: String, data: ByteArray): ByteArray
    fun decryptDataWithAES(keyAlias: String, data: ByteArray): ByteArray
}