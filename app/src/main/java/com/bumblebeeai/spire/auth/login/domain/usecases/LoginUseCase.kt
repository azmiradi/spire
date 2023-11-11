package com.bumblebeeai.spire.auth.login.domain.usecases

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.domain.usecases.UseCaseRemoteThenCache
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
        val result = body?.let {
            repository.login(it)
        } ?: throw BaseException.Local.RequestValidation(this::class, 0)
        emit(result)
    }

    override fun performLocalOperation(domain: Login): Boolean {
        return if (domain.accessToken != null && domain.merchantId != null)
            true
        else
            throw BaseException.Network.Retrial(
                messageRes = 0,
                message = null
            )
    }

    override suspend fun executeLocalOperation(domain: Login, body: LoginRequest?) {
        repository.saveMerchantId(domain.merchantId!!)
        repository.saveToken(domain.accessToken!!)
    }
}