package com.bumblebeeai.spire.home.jobs.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bumblebeeai.spire.common.MAX_PAGE_SIZE
import com.bumblebeeai.spire.common.domain.model.enums.JobType
import com.bumblebeeai.spire.home.jobs.data.datasources.local.IJobsLocalDataSource
import com.bumblebeeai.spire.home.jobs.data.datasources.remote.IJobsRemoteDataSource
import com.bumblebeeai.spire.home.jobs.data.repositories.paging.JobsPagingSource
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import com.bumblebeeai.spire.home.jobs.domain.repositories.IJobsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class JobsRepository @Inject constructor(
    private val localDataSource: IJobsLocalDataSource,
    private val remoteDataSource: IJobsRemoteDataSource,
) : IJobsRepository {
    override fun getJobs(jobType: JobType): Flow<PagingData<DriverJob>> {
        return Pager(config = PagingConfig(pageSize = MAX_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                JobsPagingSource(remoteDataSource, localDataSource, jobType)
            }).flow
    }
}