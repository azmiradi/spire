package com.azmiradi.android_base.helpers.encryption.enums

import android.security.keystore.KeyProperties

internal enum class EncryptionAlgorithm(
    val algorithm: String,
    val blockMode: String? = null,
    val padding: String,
) {
    RSA_PKCS1_PADDING(
        "RSA",
        padding = KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1,
    ),
    AES_GCM_NO_PADDING(
        "AES",
        blockMode = KeyProperties.BLOCK_MODE_GCM,
        padding = KeyProperties.ENCRYPTION_PADDING_NONE,
    ),
    AES_CBC_PKCS7_PADDING(
        "AES",
        blockMode = KeyProperties.BLOCK_MODE_CBC,
        padding = KeyProperties.ENCRYPTION_PADDING_PKCS7,
    );

    fun getTransformation(): String {
        return "$algorithm/$blockMode/$padding"
    }
}
