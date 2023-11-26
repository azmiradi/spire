package com.bumblebeeai.spire.job_details.domain.models

import com.google.android.gms.maps.model.LatLng

data class LocationDirection(
    val points: List<LatLng>? = null,
    val duration: String? = null,
    val distance: String? = null
)