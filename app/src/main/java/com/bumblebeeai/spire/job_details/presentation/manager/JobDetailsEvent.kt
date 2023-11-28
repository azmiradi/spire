package com.bumblebeeai.spire.job_details.presentation.manager

import com.bumblebeeai.spire.job_details.domain.models.UpdateJobStatusRequest
import com.google.android.gms.maps.model.LatLng

internal sealed class JobDetailsEvent(
    updateJobStatusRequest: UpdateJobStatusRequest? = null,
    origin: LatLng? = null, destination: LatLng? = null,
    jobID: String? = null,
) {
    data class UpdateJobDetails(val updateJobStatusRequest: UpdateJobStatusRequest) :
        JobDetailsEvent(updateJobStatusRequest)

    data class GetJobDetails(val jobID: String) :
        JobDetailsEvent(jobID = jobID)

    data class GetJobLocationDirections(val origin: LatLng, val destination: LatLng) :
        JobDetailsEvent(origin = origin, destination = destination)
}