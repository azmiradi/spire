package com.bumblebeeai.spire.auth.login.data.datasources.local

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.azmiradi.kotlin_base.utilities.extensions.toJson
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest
import com.bumblebeeai.spire.auth.login.domain.models.keys.KeyValue
import javax.inject.Inject

internal class LoginLocalDatasource @Inject constructor(private val storageKeyValue: IStorageKeyValue) :
    ILoginLocalDatasource {
    override suspend fun saveToken(token: String) {
        storageKeyValue.saveSecuredValue(
            KeyValue.TOKEN,
            KeyValue.TOKEN,
            token.encodeToByteArray(),
            authenticationRequired = false
        )
    }

    override suspend fun saveMerchantId(merchantId: String) {
        storageKeyValue.saveSecuredValue(
            KeyValue.MERCHANT_ID,
            KeyValue.MERCHANT_ID,
            merchantId.encodeToByteArray(),
            authenticationRequired = false
        )
    }

    override suspend fun saveLogin(loginRequest: LoginRequest) {
        storageKeyValue.saveSecuredValue(
            KeyValue.LOGIN_INFO,
            KeyValue.LOGIN_INFO,
            loginRequest.toJson().encodeToByteArray(),
            authenticationRequired = true
        )
    }
}