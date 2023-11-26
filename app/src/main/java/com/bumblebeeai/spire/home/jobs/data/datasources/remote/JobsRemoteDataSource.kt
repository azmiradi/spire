package com.bumblebeeai.spire.home.jobs.data.datasources.remote

import com.azmiradi.kotlin_base.domain.repository.remote.INetworkProvider
import com.azmiradi.kotlin_base.utilities.extensions.typeOf
import com.bumblebeeai.spire.common.domain.model.BaseDataResponse
import com.bumblebeeai.spire.common.domain.model.BaseListResponse
import com.bumblebeeai.spire.home.jobs.data.models.dto.DriverJobDto
import javax.inject.Inject

class JobsRemoteDataSource @Inject constructor(
    private val networkProvider: INetworkProvider,
) : IJobsRemoteDataSource {
    override suspend fun getJobs(query: Map<String, Any>) =
        networkProvider.get<DriverJobDto>(
            responseWrappedModel = typeOf<DriverJobDto>(),
            pathUrl = JOBS_LIST_END_POINT,
            queryParams = query
        )

}