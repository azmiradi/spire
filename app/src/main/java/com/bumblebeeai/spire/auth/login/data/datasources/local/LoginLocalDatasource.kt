package com.bumblebeeai.spire.auth.login.data.datasources.local

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.azmiradi.kotlin_base.utilities.extensions.toJson
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest
import com.bumblebeeai.spire.auth.login.domain.models.keys.KeyValue
import com.bumblebeeai.spire.common.domain.model.enums.CommenKeyValue

import javax.inject.Inject

internal class LoginLocalDatasource @Inject constructor(private val storageKeyValue: IStorageKeyValue) :
    ILoginLocalDatasource {
    override suspend fun saveToken(token: String) {
        storageKeyValue.saveString(
            CommenKeyValue.TOKEN,
            token,
        )
    }

    override suspend fun saveMerchantId(merchantId: String) {
        storageKeyValue.saveString(
            CommenKeyValue.MERCHANT_ID,
            merchantId
        )
    }

    override suspend fun saveLogin(loginRequest: LoginRequest) {
        storageKeyValue.saveSecuredValue(
            KeyValue.LOGIN_INFO,
            KeyValue.LOGIN_INFO,
            loginRequest.toJson().encodeToByteArray(),
            authenticationRequired = false
        )
    }
}