package com.bumblebeeai.spire.auth.login.data.datasources.remote

import com.azmiradi.kotlin_base.domain.repository.remote.INetworkProvider
import com.bumblebeeai.spire.auth.login.data.datasources.LOGIN_END_POINT
import com.bumblebeeai.spire.auth.login.data.model.dto.LoginDto
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest
import javax.inject.Inject

internal class LoginRemoteDataSource @Inject constructor(private val networkProvider: INetworkProvider) :
    ILoginRemoteDatasource {
    override suspend fun login(loginRequest: LoginRequest): LoginDto {
        return networkProvider.post(
            responseWrappedModel = LoginDto::class.java,
            pathUrl = LOGIN_END_POINT,
            requestBody = loginRequest
        )
    }
}