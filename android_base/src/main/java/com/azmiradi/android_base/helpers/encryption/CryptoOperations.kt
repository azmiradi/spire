package com.azmiradi.android_base.helpers.encryption

import androidx.annotation.Keep
import com.azmiradi.android_base.helpers.encryption.enums.EncryptionAlgorithm
import com.azmiradi.kotlin_base.domain.encryption.ICryptoOperations
import com.azmiradi.kotlin_base.domain.encryption.IKeyStoreManger
import com.azmiradi.kotlin_base.utilities.extensions.getModelFromJSON
import com.azmiradi.kotlin_base.utilities.extensions.toJson
import java.util.Date
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec


class CryptoOperations(private val keyStoreManger: IKeyStoreManger) : ICryptoOperations {
    @Keep
    private class CryptoData(val iv: ByteArray, val data: ByteArray)

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

    override fun encryptDataWithAES(
        keyAlias: String,
        keyValidityEnd: Date?,
        data: ByteArray,
        authenticationRequired: Boolean,
    ): ByteArray {
        val encryptionAlgorithm = EncryptionAlgorithm.AES_GCM_NO_PADDING

        val secretKey = keyStoreManger.getSecretKey(
            keyAlias = keyAlias,
            keyValidityEnd = keyValidityEnd,
            authenticationRequired = authenticationRequired
        )

        val cipher =
            Cipher.getInstance(encryptionAlgorithm.getTransformation())
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val ivBytes = cipher.iv
        val encryptedBytes = cipher.doFinal(data)
        return CryptoData(iv = ivBytes, data = encryptedBytes).toJson().encodeToByteArray()
    }

    override fun decryptDataWithAES(keyAlias: String, data: ByteArray): ByteArray {
        val encryptionAlgorithm = EncryptionAlgorithm.AES_GCM_NO_PADDING

        val secretKey = keyStoreManger.getSecretKey(keyAlias)
        val cryptoData = data.decodeToString().getModelFromJSON<CryptoData>(CryptoData::class.java)

        val cipher =
            Cipher.getInstance(encryptionAlgorithm.getTransformation())
        val spec = IvParameterSpec(cryptoData.iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
        return cipher.doFinal(cryptoData.data)
    }
}