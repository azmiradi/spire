package com.bumblebeeai.spire.job_details.data.repositories

import com.bumblebeeai.spire.job_details.data.datasources.remote.IJobDetailsRemoteDataSource
import com.bumblebeeai.spire.job_details.data.models.mapper.UpdateStatusMapper
import com.bumblebeeai.spire.job_details.domain.repositories.IJobDetailsRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class JobDetailsRepository @Inject constructor(private val remoteDataSource: IJobDetailsRemoteDataSource) :
    IJobDetailsRepository {
    override suspend fun updateJobStatus(
        status: RequestBody,
        jobId: RequestBody,
        merchantId: RequestBody,
        odometer: RequestBody?,
        faults: RequestBody?,
        outcomes: RequestBody?,
        notes: RequestBody?,
        images: List<MultipartBody.Part>?,
        signature: MultipartBody.Part?,
    ): String {
        return UpdateStatusMapper.dtoToDomain(
            remoteDataSource.updateJobStatus(
                status, jobId, merchantId, odometer, faults, outcomes
            )
        )
    }
}