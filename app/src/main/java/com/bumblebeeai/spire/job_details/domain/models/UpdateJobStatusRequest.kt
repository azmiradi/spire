package com.bumblebeeai.spire.job_details.domain.models

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class UpdateJobStatusRequest(
    val status: RequestBody,
    val jobId: RequestBody,
    val merchantId: RequestBody,
    val odometer: RequestBody?,
    val faults: RequestBody?,
    val outcomes: RequestBody?,
    val notes: RequestBody?,
    val images: List<MultipartBody.Part>?,
    val signature: MultipartBody.Part?,
)