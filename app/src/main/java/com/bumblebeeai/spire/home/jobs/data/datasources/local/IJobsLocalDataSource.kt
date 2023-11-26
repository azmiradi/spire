package com.bumblebeeai.spire.home.jobs.data.datasources.local

interface IJobsLocalDataSource {
    suspend fun getMerchantId(): String
}