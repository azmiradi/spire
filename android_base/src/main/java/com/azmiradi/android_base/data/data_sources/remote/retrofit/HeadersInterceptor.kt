package com.azmiradi.android_base.data.data_sources.remote.retrofit

import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeadersInterceptor(private val headers: Map<String, String>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .headers(headers.toHeaders())
            .build()
        return chain.proceed(modifiedRequest)
    }
}