package com.bumblebeeai.spire.job_details.domain.repositories

import okhttp3.MultipartBody
import okhttp3.RequestBody

internal interface IJobDetailsRepository {
     suspend fun updateJobStatus(
        status: RequestBody,
        jobId: RequestBody,
        merchantId: RequestBody,
        odometer: RequestBody?,
        faults: RequestBody?,
        outcomes: RequestBody?,
        notes: RequestBody?,
        images: List<MultipartBody.Part>?,
        signature: MultipartBody.Part?,
    ): String
}