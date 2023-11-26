package com.bumblebeeai.spire.home.jobs.domain.usecases

import androidx.paging.PagingData
import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.domain.usecases.UseCaseRemote
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.common.domain.model.enums.JobType
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import com.bumblebeeai.spire.home.jobs.domain.repositories.IJobsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetJobsUseCase @Inject constructor(private val repository: IJobsRepository){
    operator fun invoke(body: JobType): Flow<PagingData<DriverJob>> {
        return repository.getJobs(body)
    }
}