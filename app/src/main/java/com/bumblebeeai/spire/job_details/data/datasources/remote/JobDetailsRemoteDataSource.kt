package com.bumblebeeai.spire.job_details.data.datasources.remote

import com.azmiradi.kotlin_base.domain.repository.remote.INetworkProvider
import com.azmiradi.kotlin_base.utilities.extensions.typeOf
import com.bumblebeeai.spire.common.domain.model.BaseDataResponse
import com.bumblebeeai.spire.home.jobs.data.models.dto.DriverJobItemDto
import com.bumblebeeai.spire.job_details.data.models.dto.UpdateStatusJobDto
import com.bumblebeeai.spire.job_details.domain.models.JobDetailsRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

internal class JobDetailsRemoteDataSource @Inject constructor(private val networkProvider: INetworkProvider) :
    IJobDetailsRemoteDataSource {
    override suspend fun updateJobStatus(
        status: RequestBody,
        jobId: RequestBody,
        merchantId: RequestBody?,
        odometer: RequestBody?,
        faults: RequestBody?,
        outcomes: RequestBody?,
        notes: RequestBody?,
        images: List<MultipartBody.Part>?,
        signature: MultipartBody.Part?,
    ): UpdateStatusJobDto {
        return networkProvider.postWitParts(
            typeOf<UpdateStatusJobDto>(),
            UPDATE_STATUS_END_POINT,
            mapOf(
                "status" to status,
                "job_id" to jobId,
                "merchant_id" to merchantId,
                "odometer" to odometer,
                "faults" to faults,
                "outcomes" to outcomes,
                "notes" to notes
            ),
            fileList = images,
            files = arrayOf(signature)
        )
    }

    override suspend fun getJobDetails(
        body: JobDetailsRequest,
    ): BaseDataResponse<DriverJobItemDto> {
        return networkProvider.get(
            typeOf<BaseDataResponse<DriverJobItemDto>>(),
            JOB_VIEW_END_POINT,
            queryParams = mapOf(
                "merchant_id" to body.merchantId,
                "job_id" to body.jobID
            )
        )
    }
}