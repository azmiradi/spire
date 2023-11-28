package com.bumblebeeai.spire.job_details.domain.repositories

import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import com.bumblebeeai.spire.job_details.domain.models.JobDetailsRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

internal interface IJobDetailsRepository {
     suspend fun updateJobStatus(
        status: RequestBody,
        jobId: RequestBody,
        merchantId: RequestBody?,
        odometer: RequestBody?,
        faults: RequestBody?,
        outcomes: RequestBody?,
        notes: RequestBody?,
        images: List<MultipartBody.Part>?,
        signature: MultipartBody.Part?,
     ): String

   suspend fun getJobDetails(
      body: JobDetailsRequest
   ): DriverJob

}