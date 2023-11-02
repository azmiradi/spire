package com.azmiradi.android_base.data.data_sources.remote.factory

import com.azmiradi.android_base.R
import com.azmiradi.kotlin_base.data.exception.BaseException
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CustomCall(
    private val delegate: Call<ResponseBody>,
    private val errorConverter: Converter<ResponseBody, BaseException>
) : Call<ResponseBody> by delegate {

    override fun execute(): retrofit2.Response<ResponseBody> {
        throw UnsupportedOperationException("LeonCall doesn't support execute()")
    }

    override fun clone(): Call<ResponseBody> {
        return CustomCall(delegate.clone(), errorConverter)
    }

    override fun enqueue(callback: Callback<ResponseBody>) {
        delegate.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    val failure = provideHttpErrorCode(response.code(), response.errorBody())
                    callback.onFailure(call, failure)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val exception = when (t) {
                    is SocketTimeoutException, is UnknownHostException, is IOException -> BaseException.Network.Retrial(
                        messageRes = R.string.error_io_unexpected_message,
                        message = "Retrial network error."
                    )

                    else -> BaseException.Network.Unhandled(
                        messageRes = R.string.error_unexpected_message,
                        message = "NetworkException Unhandled error."
                    )
                }

                callback.onFailure(call, exception)
            }
        })
    }

    private fun provideHttpErrorCode(code: Int, errorBody: ResponseBody?): BaseException =
        when (code) {
            401 -> BaseException.Client.Unauthorized

            in 400..499 -> buildClientException(code, errorBody)

            in 500..599 -> BaseException.Server.InternalServerError(
                httpErrorCode = code, errorBody?.string()
            )

            else -> BaseException.Client.Unhandled(httpErrorCode = code, errorBody?.string())
        }

    private fun buildClientException(code: Int, errorBody: ResponseBody?): BaseException {
        return if (errorBody == null) BaseException.Client.Unhandled(
            httpErrorCode = code, "There is no error body for this code."
        ) else try {
            errorConverter.convert(errorBody)
                ?: BaseException.Client.Unhandled(
                    httpErrorCode = code, "Error body could not be converted."
                )
        } catch (e: Exception) {
            BaseException.Client.Unhandled(httpErrorCode = code, e.message)
        }
    }
}