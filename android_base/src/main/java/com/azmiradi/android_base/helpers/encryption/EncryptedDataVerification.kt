package com.azmiradi.android_base.helpers.encryption

import android.util.Base64
import com.azmiradi.kotlin_base.domain.encryption.IEncryptedDataVerification
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.Signature
import java.security.cert.Certificate

class EncryptedDataVerification : IEncryptedDataVerification {
    override fun verificationSignature(
        signatureData: String,
        encryptedData: String,
        certificate: Certificate?
    ): Boolean {
        if (certificate != null) {
            val signature: ByteArray = Base64.decode(signatureData, Base64.DEFAULT)

            val isValid: Boolean = Signature.getInstance("SHA256withRSA").run {
                initVerify(certificate)
                update(encryptedData.toByteArray())
                verify(signature)
            }
            return isValid
        }
        return false
    }

    override fun calculateHash(encryptedData: String, comperedHash: String): Boolean {
        try {
            val md = MessageDigest.getInstance("SHA-256")
            val hashBytes = md.digest(encryptedData.toByteArray())

            val hexString = StringBuilder()
            for (hashByte in hashBytes) {
                val hex = Integer.toHexString(0xff and hashByte.toInt())
                if (hex.length == 1) {
                    hexString.append('0')
                }
                hexString.append(hex)
            }

            return hexString.toString() == comperedHash
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            return false
        }
    }
}