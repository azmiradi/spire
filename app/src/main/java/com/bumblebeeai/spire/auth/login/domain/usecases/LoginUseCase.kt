package com.bumblebeeai.spire.auth.login.domain.usecases

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.domain.usecases.UseCaseRemoteThenCache
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.auth.login.domain.models.Login
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest
import com.bumblebeeai.spire.auth.login.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class LoginUseCase
@Inject constructor(private val repository: ILoginRepository) :
    UseCaseRemoteThenCache<Login, LoginRequest>() {
    override fun executeRemoteDS(body: LoginRequest?): Flow<Login> = flow {
        emit(repository.login(body!!))
    }

    override suspend fun performLocalOperation(domain: Login): Boolean {
        if (!domain.accessToken.isNullOrEmpty() && !domain.merchantId.isNullOrEmpty()) {
            return true
        } else throw BaseException.Network.Retrial(
            R.string.invalid_login_info,
            "Invalid Login Info"
        )
    }

    override suspend fun executeLocalOperation(domain: Login, body: LoginRequest?) {
        repository.saveMerchantId(domain.merchantId!!)
        repository.saveToken(domain.accessToken!!)
    }
}