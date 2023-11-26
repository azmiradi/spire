package com.azmiradi.android_base.data.data_sources.remote.retrofit.factory

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class CustomCallAdapterFactory private constructor(private val errorClassType: Type) :
    CallAdapter.Factory() {
    companion object {
        fun create(errorClassType: Type): CallAdapter.Factory {
            return CustomCallAdapterFactory(errorClassType)
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

        val converter: Converter<ResponseBody,Any> =
            retrofit.responseBodyConverter(errorClassType, annotations)

        return CustomCallAdapter(converter)
    }
}
