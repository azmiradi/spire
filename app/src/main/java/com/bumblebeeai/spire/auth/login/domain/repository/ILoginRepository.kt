package com.bumblebeeai.spire.auth.login.domain.repository

import com.bumblebeeai.spire.auth.login.domain.models.Login
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest

internal interface ILoginRepository  {
    suspend fun login(loginRequest: LoginRequest): Login
    suspend fun saveToken(token: String)
    suspend fun saveMerchantId(merchantId: String)
}