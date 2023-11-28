package com.bumblebeeai.spire.job_details.data.datasources.remote

import com.bumblebeeai.spire.common.domain.model.BaseDataResponse
import com.bumblebeeai.spire.home.jobs.data.models.dto.DriverJobItemDto
import com.bumblebeeai.spire.job_details.data.models.dto.UpdateStatusJobDto
import com.bumblebeeai.spire.job_details.domain.models.JobDetailsRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IJobDetailsRemoteDataSource {
    suspend fun updateJobStatus(
        status: RequestBody,
        jobId: RequestBody,
        merchantId: RequestBody?=null,
        odometer: RequestBody? = null,
        faults: RequestBody? = null,
        outcomes: RequestBody? = null,
        notes: RequestBody? = null,
        images: List<MultipartBody.Part>? = null,
        signature: MultipartBody.Part? = null,
    ): UpdateStatusJobDto

    suspend fun getJobDetails(
        body: JobDetailsRequest
    ): BaseDataResponse<DriverJobItemDto>
}