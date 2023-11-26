package com.bumblebeeai.spire.job_details.presentation.manager

import com.bumblebeeai.spire.job_details.domain.models.UpdateJobStatusRequest
import com.google.android.gms.maps.model.LatLng

internal sealed class JobDetailsEvent(
    updateJobStatusRequest: UpdateJobStatusRequest? = null,
    jobID: Int? = null, currentLocation: LatLng? = null,
) {
    data class UpdateJobDetails(val updateJobStatusRequest: UpdateJobStatusRequest) :
        JobDetailsEvent(updateJobStatusRequest)

    data class GetJobDetails(val jobID: Int, val currentLocation: LatLng) :
        JobDetailsEvent(jobID = jobID, currentLocation = currentLocation)

}