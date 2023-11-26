package com.bumblebeeai.spire.job_details.domain.usecases

import com.azmiradi.kotlin_base.domain.usecases.UseCaseRemote
import com.bumblebeeai.spire.job_details.domain.models.UpdateJobStatusRequest
import com.bumblebeeai.spire.job_details.domain.repositories.IJobDetailsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class UpdateJobStatusUseCase @Inject constructor(private val repository: IJobDetailsRepository) :
    UseCaseRemote<String, UpdateJobStatusRequest>() {
    override fun executeRemoteDS(body: UpdateJobStatusRequest?) = flow {
        body?.let {
            emit(
                repository.updateJobStatus(
                    body.status,
                    body.jobId,
                    body.merchantId,
                    body.odometer,
                    body.faults,
                    body.outcomes,
                    body.notes,
                    body.images,
                    body.signature,
                )
            )
        }
    }
}