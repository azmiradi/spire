package com.bumblebeeai.spire.auth.login.data.datasources.local

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.bumblebeeai.spire.common.keys.KeyAliasValue
import com.bumblebeeai.spire.common.keys.KeyValue
import javax.inject.Inject

internal class LoginLocalDatasource @Inject constructor(private val storageKeyValue: IStorageKeyValue) :
    ILoginLocalDatasource {
    override suspend fun saveToken(token: String) {
        storageKeyValue.saveSecuredValue(
            KeyValue.TOKEN,
            KeyAliasValue.TOKEN,
            token.encodeToByteArray()
        )
    }

    override suspend fun saveMerchantId(merchantId: String) {
        storageKeyValue.saveSecuredValue(
            KeyValue.TOKEN,
            KeyAliasValue.TOKEN,
            merchantId.encodeToByteArray()
        )
    }
}