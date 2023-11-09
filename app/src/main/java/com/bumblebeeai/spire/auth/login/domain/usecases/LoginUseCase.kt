package com.bumblebeeai.spire.auth.login.domain.usecases

import com.azmiradi.kotlin_base.domain.usecases.UseCaseRemoteThenCache
import com.bumblebeeai.spire.auth.login.domain.models.Login
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest
import com.bumblebeeai.spire.auth.login.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class LoginUseCase @Inject constructor(private val repository: ILoginRepository) :
    UseCaseRemoteThenCache<Login, LoginRequest>() {
    override fun executeRemoteDS(body: LoginRequest?) = flow {
        if (body?.isValidateInput() == true)
            emit(repository.login(body))
    }

    override fun performLocalOperation(domain: Login): Boolean {
        return domain.accessToken != null && domain.merchantId != null
    }

    override suspend fun executeLocalOperation(domain: Login, body: LoginRequest?) {
        repository.saveMerchantId(domain.merchantId!!)
        repository.saveToken(domain.accessToken!!)
    }
}