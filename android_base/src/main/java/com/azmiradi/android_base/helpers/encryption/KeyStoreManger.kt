package com.azmiradi.android_base.helpers.encryption

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.azmiradi.android_base.helpers.encryption.enums.EncryptionAlgorithm
import com.azmiradi.kotlin_base.domain.encryption.IKeyStoreManger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.Certificate
import java.util.Date
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class KeyStoreManger : IKeyStoreManger {
    private val keyStore: KeyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private fun createKeyPair(
        keyAlias: String,
        keyValidityEnd: Date?
    ): KeyPair {
        return KeyPairGenerator.getInstance(EncryptionAlgorithm.RSA_PKCS1_PADDING.algorithm).apply {
            initialize(
                KeyGenParameterSpec.Builder(
                    keyAlias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                ).setEncryptionPaddings(EncryptionAlgorithm.RSA_PKCS1_PADDING.padding)
                    .setKeyValidityEnd(keyValidityEnd)
                    .build()
            )
        }.generateKeyPair()
    }

    private fun createSecretKey(
        keyAlias: String,
        keyValidityEnd: Date?
    ): SecretKey {
        return KeyGenerator.getInstance(EncryptionAlgorithm.AES_CBC_PKCS7.algorithm).apply {
            init(
                KeyGenParameterSpec.Builder(
                    keyAlias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                ).setBlockModes(EncryptionAlgorithm.AES_CBC_PKCS7.blockMode)
                    .setEncryptionPaddings(EncryptionAlgorithm.AES_CBC_PKCS7.padding)
                    .setKeyValidityEnd(keyValidityEnd)
                    .build()
            )
        }.generateKey()
    }

    override fun getPublicKey(keyAlias: String, keyValidityEnd: Date?): PublicKey {
        val publicKey: PublicKey? = keyStore.getCertificate(keyAlias)?.publicKey
        return publicKey ?: createKeyPair(
            keyAlias,
            keyValidityEnd
        ).public
    }

    override fun getPrivateKey(keyAlias: String, keyValidityEnd: Date?): PrivateKey {
        val privateKey: PrivateKey? = keyStore.getKey(keyAlias, null) as PrivateKey?
        return privateKey ?: createKeyPair(
            keyAlias,
            keyValidityEnd
        ).private
    }

    override fun getCertification(keyAlias: String): Certificate? {
        return keyStore.getCertificate(keyAlias)
    }

    override fun getSecretKey(keyAlias: String, keyValidityEnd: Date?): SecretKey {
        val existingKey = keyStore.getEntry(keyAlias, null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createSecretKey(keyAlias, keyValidityEnd)
    }

    override fun removeKeyStore(keyAlias: String) {
        if (keyStore.containsAlias(keyAlias))
            keyStore.deleteEntry(keyAlias)
    }
}