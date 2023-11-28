package com.bumblebeeai.spire.job_details.domain.usecases

import com.azmiradi.kotlin_base.domain.usecases.UseCaseRemote
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import com.bumblebeeai.spire.job_details.domain.models.JobDetailsRequest
import com.bumblebeeai.spire.job_details.domain.repositories.IJobDetailsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class GetJobDetailsUseCase @Inject constructor(private val repository: IJobDetailsRepository) :
    UseCaseRemote<DriverJob, JobDetailsRequest>() {
    override fun executeRemoteDS(body: JobDetailsRequest?) = flow {
        emit(repository.getJobDetails(body!!))
    }
}