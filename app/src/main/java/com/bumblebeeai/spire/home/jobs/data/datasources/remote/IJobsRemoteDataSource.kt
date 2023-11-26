package com.bumblebeeai.spire.home.jobs.data.datasources.remote

import com.bumblebeeai.spire.common.domain.model.BaseDataResponse
import com.bumblebeeai.spire.common.domain.model.BaseListResponse
import com.bumblebeeai.spire.home.jobs.data.models.dto.DriverJobDto

interface IJobsRemoteDataSource {
    suspend fun getJobs(
        query: Map<String,Any>
    ): DriverJobDto
}