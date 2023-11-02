package com.azmiradi.kotlin_base.domain.encryption

import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.Certificate
import java.util.Date
import javax.crypto.SecretKey

interface IKeyStoreManger {
    fun getPublicKey(keyAlias: String, keyValidityEnd: Date?=null): PublicKey
    fun getPrivateKey(keyAlias: String, keyValidityEnd: Date? = null): PrivateKey
    fun getCertification(keyAlias: String): Certificate?
    fun getSecretKey(keyAlias: String, keyValidityEnd: Date? = null):SecretKey
    fun removeKeyStore(keyAlias: String)
}