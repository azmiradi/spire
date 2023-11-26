package com.bumblebeeai.spire.job_details.data.datasources.remote

import com.bumblebeeai.spire.job_details.data.models.dto.UpdateStatusJobDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

interface IJobDetailsRemoteDataSource {
    suspend fun updateJobStatus(
        @Part("status") status: RequestBody,
        @Part("job_id") jobId: RequestBody,
        @Part("merchant_id") merchantId: RequestBody,
        @Part("odometer") odometer: RequestBody? = null,
        @Part("faults") faults: RequestBody? = null,
        @Part("outcomes") outcomes: RequestBody? = null,
        @Part("notes") notes: RequestBody? = null,
        @Part("upload_images") images: List<MultipartBody.Part>? = null,
        @Part("signature") signature: MultipartBody.Part? = null,
    ): UpdateStatusJobDto
}