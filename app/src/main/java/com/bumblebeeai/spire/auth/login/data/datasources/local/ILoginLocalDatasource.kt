package com.bumblebeeai.spire.auth.login.data.datasources.local

internal interface ILoginLocalDatasource {
    suspend fun saveToken(token: String)
    suspend fun saveMerchantId(merchantId: String)
}