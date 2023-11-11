package com.bumblebeeai.spire.auth.login.data.datasources.local

import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest

internal interface ILoginLocalDatasource {
    suspend fun saveToken(token: String)
    suspend fun saveMerchantId(merchantId: String)

    suspend fun saveLogin(loginRequest: LoginRequest)
}