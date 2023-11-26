package com.azmiradi.android_base.data.data_sources.remote.retrofit

import androidx.annotation.Keep
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.QueryMap

@Keep interface ApiService {
    @DELETE("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun delete(
        @Path(value = "pathUrl", encoded = true) pathUrl: String, @Body body: Any,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>
    ): ResponseBody

    @POST("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun post(
        @Path(value = "pathUrl", encoded = true) pathUrl: String, @Body body: Any,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>
    ): ResponseBody

    @PUT("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun put(
        @Path(value = "pathUrl", encoded = true) pathUrl: String, @Body body: Any,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>
    ): ResponseBody

    @POST("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun postWithHeaderResponse(
        @Path(value = "pathUrl", encoded = true) pathUrl: String, @Body body: Any,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>
    ): Response<ResponseBody>

    @GET("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun get(
        @Path(value = "pathUrl", encoded = true) pathUrl: String,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>,
    ): ResponseBody

    @GET("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun getWithHeaderResponse(
        @Path(value = "pathUrl", encoded = true) pathUrl: String,
        @HeaderMap headerMap: Map<String, Any>, @QueryMap queryParams: Map<String, Any>,
    ): Response<ResponseBody>

    @Multipart
    @POST("{pathUrl}")
    @JvmSuppressWildcards
    suspend fun postWitParts(
        @Path(value = "pathUrl", encoded = true) pathUrl: String,
        @PartMap partMap: Map<String, Any?>,
        fileList: List<Any>?,
        @Part vararg files: Any?,
    ): ResponseBody
}