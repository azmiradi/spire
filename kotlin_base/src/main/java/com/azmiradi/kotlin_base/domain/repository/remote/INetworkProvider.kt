package com.azmiradi.kotlin_base.domain.repository.remote

import java.lang.reflect.Type

interface INetworkProvider {

    suspend fun <ResponseBody, RequestBody> delete(
        responseWrappedModel: Type, pathUrl: String, headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null, requestBody: RequestBody? = null
    ): ResponseBody

    suspend fun <ResponseBody, RequestBody> post(
        responseWrappedModel: Type, pathUrl: String, headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null, requestBody: RequestBody? = null
    ): ResponseBody

    suspend fun <ResponseBody, RequestBody> postWithHeaderResponse(
        responseWrappedModel: Type, pathUrl: String, headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null, requestBody: RequestBody? = null
    ): Pair<ResponseBody, Map<String, String>>

    suspend fun <ResponseBody, RequestBody> put(
        responseWrappedModel: Type, pathUrl: String, headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null, requestBody: RequestBody? = null
    ): ResponseBody

    suspend fun <ResponseBody> get(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null,
    ): ResponseBody

    suspend fun <ResponseBody> getWithHeaderResponse(
        responseWrappedModel: Type, pathUrl: String, headers: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null,
    ): Pair<ResponseBody, Map<String, String>>


    suspend fun <ResponseBody> postWitParts(
        responseWrappedModel: Type, pathUrl: String,
        partMap: Map<String, Any?>,
        fileList: List<Any>?,
        vararg files: Any?,
    ): ResponseBody
}