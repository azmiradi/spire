package com.bumblebeeai.spire.home.jobs.domain.repositories

import androidx.paging.PagingData
import com.bumblebeeai.spire.common.domain.model.enums.JobType
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import kotlinx.coroutines.flow.Flow

internal interface IJobsRepository {
     fun getJobs(jobType: JobType): Flow<PagingData<DriverJob>>
}