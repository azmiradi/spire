package com.bumblebeeai.spire.auth.login.data.repository

import com.bumblebeeai.spire.auth.login.data.datasources.local.ILoginLocalDatasource
import com.bumblebeeai.spire.auth.login.data.datasources.remote.ILoginRemoteDatasource
import com.bumblebeeai.spire.auth.login.data.mapper.LoginMapper
import com.bumblebeeai.spire.auth.login.domain.models.Login
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest
import com.bumblebeeai.spire.auth.login.domain.repository.ILoginRepository
import javax.inject.Inject

internal class LoginRepository @Inject constructor(
    private val localDatasource: ILoginLocalDatasource,
    private val remoteDatasource: ILoginRemoteDatasource,
) : ILoginRepository {
    override suspend fun login(loginRequest: LoginRequest): Login {
        return LoginMapper.dtoToDomain(remoteDatasource.login(loginRequest))
    }

    override suspend fun saveToken(token: String) {
        localDatasource.saveToken(token)
    }

    override suspend fun saveMerchantId(merchantId: String) {
        localDatasource.saveMerchantId(merchantId)
    }

    override suspend fun saveLogin(loginRequest: LoginRequest) {
        localDatasource.saveLogin(loginRequest = loginRequest)
    }
}