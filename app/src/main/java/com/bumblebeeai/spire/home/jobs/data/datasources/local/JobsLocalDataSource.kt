package com.bumblebeeai.spire.home.jobs.data.datasources.local

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.bumblebeeai.spire.common.domain.model.enums.CommenKeyValue
import javax.inject.Inject

internal class JobsLocalDataSource @Inject constructor(private val storageKeyValue: IStorageKeyValue) :
    IJobsLocalDataSource {
    override suspend fun getMerchantId(): String {
        return storageKeyValue.getString(
            CommenKeyValue.MERCHANT_ID
        )
    }
}