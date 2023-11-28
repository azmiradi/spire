package com.bumblebeeai.spire.job_details.domain.models

import com.google.errorprone.annotations.Keep

@Keep
data class JobDetailsRequest(
    val jobID: String,
    val merchantId: String,
)