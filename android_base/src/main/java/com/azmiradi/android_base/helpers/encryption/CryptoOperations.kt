package com.azmiradi.android_base.helpers.encryption

import com.azmiradi.android_base.helpers.encryption.enums.EncryptionAlgorithm
import com.azmiradi.kotlin_base.domain.encryption.ICryptoOperations
import com.azmiradi.kotlin_base.domain.encryption.IKeyStoreManger
import javax.crypto.Cipher

class CryptoOperations(private val keyStoreManger: IKeyStoreManger) : ICryptoOperations {
    override fun encryptDataWitRSA(keyAlias: String, data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(EncryptionAlgorithm.RSA_PKCS1_PADDING.getTransformation())
        cipher.init(Cipher.ENCRYPT_MODE, keyStoreManger.getPublicKey(keyAlias))
        return cipher.doFinal(data)
    }

    override fun decryptDataWithRSA(keyAlias: String, data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(EncryptionAlgorithm.RSA_PKCS1_PADDING.getTransformation())
        cipher.init(Cipher.DECRYPT_MODE, keyStoreManger.getPrivateKey(keyAlias))
        return cipher.doFinal(data)
    }

    override fun encryptDataWithAES(keyAlias: String, data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(EncryptionAlgorithm.AES_CBC_PKCS7.getTransformation())
        cipher.init(Cipher.ENCRYPT_MODE, keyStoreManger.getSecretKey(keyAlias))
        return cipher.doFinal(data)
    }

    override fun decryptDataWithAES(keyAlias: String, data: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(EncryptionAlgorithm.AES_CBC_PKCS7.getTransformation())
        cipher.init(Cipher.DECRYPT_MODE, keyStoreManger.getSecretKey(keyAlias))
        return cipher.doFinal(data)
    }
}