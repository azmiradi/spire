package com.bumblebeeai.spire.job_details.domain.models

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class UpdateJobStatusRequest(
    val status: RequestBody,
    val jobId: RequestBody,
    val merchantId: RequestBody? = null,
    val odometer: RequestBody? = null,
    val faults: RequestBody? = null,
    val outcomes: RequestBody? = null,
    val notes: RequestBody? = null,
    val images: List<MultipartBody.Part>? = null,
    val signature: MultipartBody.Part? = null,
)