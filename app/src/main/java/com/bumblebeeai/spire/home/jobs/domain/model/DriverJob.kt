package com.bumblebeeai.spire.home.jobs.domain.model

import android.os.Parcelable
import com.bumblebeeai.spire.home.jobs.domain.model.enums.JobStatus
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class DriverJob(
     val date: String,
     val jobDetails: String,
     val locationAddress: String,
     val id: Int,
     val jobNumber: String?,
     val status: JobStatus,
     val contact: String?,
     val vehicleLocation: LocationDetails,
     val destinationLocation: LocationDetails? = null,
) : Parcelable {
     @Parcelize
     data class LocationDetails(
          val address: String,
          val lat: Double,
          val lung: Double,
     ) : Parcelable
}
