package com.bumblebeeai.spire.app_entry_point.navigation.data.datasource

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.bumblebeeai.spire.common.domain.model.enums.CommenKeyValue
import javax.inject.Inject

class NavigationLocalDatasource @Inject constructor(private val storageKeyValue: IStorageKeyValue) :
    INavigationLocalDatasource {
    override suspend fun isLoginUser(): Boolean {
        return storageKeyValue.hasKey(CommenKeyValue.TOKEN)
                && storageKeyValue.hasKey(CommenKeyValue.MERCHANT_ID)
    }

}