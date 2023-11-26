package com.azmiradi.android_base.data.data_sources.remote.retrofit.factory

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class CustomCallAdapter(
    private val errorConverter: Converter<ResponseBody, Any>
) : CallAdapter<ResponseBody, Call<ResponseBody>> {

    override fun responseType(): Type = ResponseBody::class.java

    override fun adapt(call: Call<ResponseBody>): Call<ResponseBody> {
        return CustomCall(call, errorConverter)
    }
}