package com.bumblebeeai.spire.job_details.presentation.screens

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.location.Location
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.azmi.location.DefaultLocationClient
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.common.ui.AppCompose
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import com.bumblebeeai.spire.home.jobs.domain.model.enums.JobStatus
import com.bumblebeeai.spire.job_details.presentation.component.JobInformationCard
import com.bumblebeeai.spire.job_details.presentation.manager.JobDetailsEvent
import com.bumblebeeai.spire.job_details.presentation.manager.JobDetailsViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
internal fun MapOrderDetailsScreen(
    jobItem: DriverJob,
    navigationController: NavHostController,
) {
    val viewModel = hiltViewModel<JobDetailsViewModel>()
    val state = viewModel.viewState.collectAsState().value
    val currentLocation = remember {
        mutableStateOf<Location?>(null)
    }
    val isLocationDetermining = remember {
        mutableStateOf(true)
    }

    val context = LocalContext.current

    DisposableEffect(Unit) {
        val defaultLocationClient = DefaultLocationClient(
            LocationServices.getFusedLocationProviderClient(context as Activity)
        )

        defaultLocationClient.getLocationUpdates{
            isLocationDetermining.value = false
            currentLocation.value = it
            viewModel.onEvent(
                JobDetailsEvent.GetJobLocationDirections(
                    LatLng(jobItem.vehicleLocation.lat, jobItem.vehicleLocation.lung),
                    LatLng(it.latitude, it.longitude)
                )
            )
        }

        onDispose(defaultLocationClient::release)
    }
    val showDetailsDialog = remember {
        mutableStateOf(false)
    }


    AppCompose(
        state
    ) {
        if (jobItem.status.isValidStatus()) {
            val currentLatLng = LatLng(
                currentLocation.value?.latitude ?: 0.0,
                currentLocation.value?.longitude ?: 0.0
            )
            val pickupLatLng = LatLng(
                jobItem.vehicleLocation.lat,
                jobItem.vehicleLocation.lung
            )

            val cameraPositionState: CameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(
                    currentLatLng, 11f
                )
            }
            val latLngBounds = remember {
                LatLngBounds.Builder()
                    .include(
                        currentLatLng
                    ).include(
                        pickupLatLng
                    )
            }

            Box(Modifier.fillMaxSize()) {
                GoogleMap(
                    cameraPositionState = cameraPositionState, onMapLoaded = {
                        cameraPositionState.move(
                            CameraUpdateFactory.newLatLngBounds(
                                latLngBounds.build(), 5
                            )
                        )
                    }, properties = MapProperties(
                        latLngBoundsForCameraTarget = latLngBounds.build(),
                        mapStyleOptions = MapStyleOptions(
                            "[\n" + "    {\n" + "        \"featureType\": \"all\",\n" + "        \"elementType\": \"labels.text.fill\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"saturation\": 36\n" + "            },\n" + "            {\n" + "                \"color\": \"#333333\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 40\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"all\",\n" + "        \"elementType\": \"labels.text.stroke\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"visibility\": \"on\"\n" + "            },\n" + "            {\n" + "                \"color\": \"#ffffff\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 16\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"all\",\n" + "        \"elementType\": \"labels.icon\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"visibility\": \"off\"\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"administrative\",\n" + "        \"elementType\": \"geometry.fill\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#fefefe\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 20\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"administrative\",\n" + "        \"elementType\": \"geometry.stroke\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#fefefe\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 17\n" + "            },\n" + "            {\n" + "                \"weight\": 1.2\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"landscape\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#edebe4\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 20\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"poi\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#f5f5f5\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 21\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"poi.park\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#dedede\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 21\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"poi.park\",\n" + "        \"elementType\": \"geometry.fill\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#d1ecc7\"\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"road.highway\",\n" + "        \"elementType\": \"geometry.fill\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#ffffff\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 17\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"road.highway\",\n" + "        \"elementType\": \"geometry.stroke\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#ffffff\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 29\n" + "            },\n" + "            {\n" + "                \"weight\": 0.2\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"road.arterial\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#ffffff\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 18\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"road.local\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#ffffff\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 16\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"transit\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#f2f2f2\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 19\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"water\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#bddddd\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 17\n" + "            }\n" + "        ]\n" + "    }\n" + "]"
                        ),
                    )
                ) {
                    MapMarker(
                        position = currentLatLng,
                        title = "Current Location",
                        iconResourceId = R.drawable.icon_pickup
                    )
                    MapMarker(
                        position = pickupLatLng,
                        title = "Pickup Location",
                        iconResourceId = R.drawable.icon_pickup2
                    )
                    state.locationDirection?.let {
                        Polyline(
                            points = it.points ?: emptyList(), color = Color(0xff50BEE8)
                        )
                    }
                }

                androidx.compose.animation.AnimatedVisibility(
                    visible = !showDetailsDialog.value,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(
                            start = 20.dp, end = 20.dp, bottom = 20.dp
                        )
                ) {
                    JobInformationCard(
                        jobItem = jobItem,
                        modifier = Modifier,
                        timeArrival = state.locationDirection?.duration
                            ?: "---",
                        distance = state.locationDirection?.distance,
                        onUpdateClick = {

                        }
                    )
                }
            }
        }
    }


    BackHandler {
        if (showDetailsDialog.value) {
            showDetailsDialog.value = false
        } else {
            navigationController.popBackStack()
        }
    }
}

fun JobStatus?.isValidStatus(): Boolean {
    return this != JobStatus.Completed
}

@Composable
fun MapMarker(
    position: LatLng, title: String, @DrawableRes iconResourceId: Int,
) {
    val context = LocalContext.current
    val icon = bitmapDescriptorFromVector(
        context, iconResourceId
    )
    Marker(
        state = MarkerState(position = position),
        title = title,
        icon = icon,
    )
}

fun bitmapDescriptorFromVector(
    context: Context, vectorResId: Int,
): BitmapDescriptor? {
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    )

    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}


