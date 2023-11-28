package com.azmi.location

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult


class DefaultLocationClient(
    private val client: FusedLocationProviderClient,
) {
    private var locationCallback: LocationCallback? = null

    @SuppressLint("MissingPermission")
    fun getLocationUpdates(onLocationGated: (Location) -> Unit) {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 20 * 1000
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    if (location != null) {
                        onLocationGated(location)
                        break
                    }
                }
            }
        }
        client.requestLocationUpdates(
            locationRequest, locationCallback!!,
            Looper.getMainLooper()
        )
    }

}