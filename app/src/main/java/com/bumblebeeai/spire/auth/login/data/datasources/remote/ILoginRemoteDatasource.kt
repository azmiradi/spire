package com.bumblebeeai.spire.auth.login.data.datasources.remote

import com.bumblebeeai.spire.auth.login.data.model.dto.LoginDto
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest

internal interface ILoginRemoteDatasource {
    suspend fun login(loginRequest: LoginRequest): LoginDto
}