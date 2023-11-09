package com.azmiradi.android_base.data.data_sources.remote.retrofit

import androidx.annotation.Keep
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap

@Keep interface ApiService {
    @DELETE("{pathUrl}")
    suspend fun delete(
        @Path(value = "pathUrl", encoded = true) pathUrl: String, @Body body: Any,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>
    ): ResponseBody

    @POST("{pathUrl}")
    suspend fun post(
        @Path(value = "pathUrl", encoded = true) pathUrl: String, @Body body: Any,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>
    ): ResponseBody

    @PUT("{pathUrl}")
    suspend fun put(
        @Path(value = "pathUrl", encoded = true) pathUrl: String, @Body body: Any,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>
    ): ResponseBody

    @POST("{pathUrl}")
    suspend fun postWithHeaderResponse(
        @Path(value = "pathUrl", encoded = true) pathUrl: String, @Body body: Any,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>
    ): Response<ResponseBody>

    @GET("{pathUrl}")
    suspend fun get(
        @Path(value = "pathUrl", encoded = true) pathUrl: String,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>
    ): ResponseBody

    @GET("{pathUrl}")
    suspend fun getWithHeaderResponse(
        @Path(value = "pathUrl", encoded = true) pathUrl: String,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>
    ): Response<ResponseBody>
}