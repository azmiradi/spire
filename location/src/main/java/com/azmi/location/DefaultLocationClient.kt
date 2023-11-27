package com.azmi.location

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng


class DefaultLocationClient(
    private val client: FusedLocationProviderClient,
) {
    private lateinit var locationCallback: LocationCallback
    @SuppressLint("MissingPermission")
    fun getLocationUpdates(onLocationGated: (Location) -> Unit) {
        val request = LocationRequest.create()
            .setInterval(1000)
            .setFastestInterval(1000)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                result.locations.lastOrNull()?.let {
                    onLocationGated(
                        it
                    )
                }
            }

            override fun onLocationAvailability(p0: LocationAvailability) {
                if (!p0.isLocationAvailable) {
                    println("GPS CLOSED")
                    return
                }
                println("GPS OPENED")
            }
        }

        client.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    fun release() {
        client.removeLocationUpdates(locationCallback)
    }
}