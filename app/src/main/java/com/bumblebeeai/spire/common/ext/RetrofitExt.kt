package com.bumblebeeai.spire.common.ext

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


fun String.convertTextToRequestBody(): RequestBody {
    return this
        .toRequestBody("text/plain".toMediaTypeOrNull())
}