package com.azmiradi.kotlin_base.domain.encryption

import java.security.cert.Certificate

interface IEncryptedDataVerification {
    fun verificationSignature(
        signatureData: String,
        encryptedData: String,
        certificate: Certificate?
    ): Boolean

    fun calculateHash(encryptedData: String, comperedHash: String): Boolean
}