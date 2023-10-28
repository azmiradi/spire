package com.azmiradi.kotlin_base.utilities.extensions

import com.azmiradi.kotlin_base.helpers.Base64


fun ByteArray.encodeToBase64(): String {
    return Base64.encoder.encode(this).decodeToString()
}

fun String.decodeFromBase64(): ByteArray {
    return Base64.decoder.decode(this.encodeToByteArray())
}
