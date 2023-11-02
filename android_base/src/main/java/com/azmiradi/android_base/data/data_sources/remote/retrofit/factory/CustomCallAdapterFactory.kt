package com.azmiradi.android_base.data.data_sources.remote.factory

import com.azmiradi.kotlin_base.data.exception.BaseException
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CustomCallAdapterFactory private constructor() : CallAdapter.Factory() {
    companion object {
        fun create(): CallAdapter.Factory {
            return CustomCallAdapterFactory()
        }
    }

    override fun get(
        returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }
        val responseType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawType = getRawType(responseType)

        if (rawType != ResponseBody::class.java) {
            return null
        }

        val errorConverter: Converter<ResponseBody, BaseException> =
            retrofit.responseBodyConverter(BaseException::class.java, annotations)

        return CustomCallAdapter(errorConverter)
    }
}
