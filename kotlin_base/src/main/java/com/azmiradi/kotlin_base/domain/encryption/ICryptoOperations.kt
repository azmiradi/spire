package com.azmiradi.kotlin_base.domain.encryption

import java.util.Date

interface ICryptoOperations {
    fun encryptDataWitRSA(keyAlias: String, data: ByteArray): ByteArray
    fun decryptDataWithRSA(keyAlias: String, data: ByteArray): ByteArray
    fun encryptDataWithAES(
        keyAlias: String,
        keyValidityEnd: Date?= null,
        data: ByteArray,
        authenticationRequired: Boolean = false,
    ): ByteArray

    fun decryptDataWithAES(keyAlias: String, data: ByteArray): ByteArray
}