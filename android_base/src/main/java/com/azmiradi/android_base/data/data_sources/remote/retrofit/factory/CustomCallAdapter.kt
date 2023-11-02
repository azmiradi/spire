package com.azmiradi.android_base.data.data_sources.remote.factory

import com.azmiradi.kotlin_base.data.exception.BaseException
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class CustomCallAdapter(
    private val errorConverter: Converter<ResponseBody, BaseException>
) : CallAdapter<ResponseBody, Call<ResponseBody>> {

    override fun responseType(): Type = ResponseBody::class.java

    override fun adapt(call: Call<ResponseBody>): Call<ResponseBody> {
        return CustomCall(call, errorConverter)
    }
}