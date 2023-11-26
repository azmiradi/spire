package com.bumblebeeai.spire.job_details.presentation.screens
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.location.Location
//import androidx.activity.compose.BackHandler
//import androidx.annotation.DrawableRes
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.Surface
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.data.EmptyGroup.data
//import androidx.compose.ui.unit.dp
//import androidx.core.content.ContextCompat
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.azmi.location.CustomLocationListener
//import com.bumblebeeai.spire.R
//import com.bumblebeeai.spire.common.ui.AppCompose
//import com.bumblebeeai.spire.home.jobs.data.models.dto.DriverJobItem
//import com.bumblebeeai.spire.job_details.presentation.manager.JobDetailsEvent
//import com.bumblebeeai.spire.job_details.presentation.manager.JobDetailsViewModel
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.model.*
//import com.google.maps.android.compose.*
//
//@Composable
//fun MapOrderDetailsScreen(
//    jobID: String,
//    onBack: () -> Unit,
//) {
//    val viewModel = hiltViewModel<JobDetailsViewModel>()
//    val state = viewModel.viewState.collectAsState().value
//    val currentLocation = remember {
//        mutableStateOf<Location?>(null)
//    }
//    val isLocationDetermining = remember {
//        mutableStateOf(true)
//    }
//
//    val context = LocalContext.current
//
//    DisposableEffect(Unit) {
//        val locationListener = CustomLocationListener(context) {
//            it?.let {
//                isLocationDetermining.value = false
//                currentLocation.value = it
//
//                viewModel.onEvent(
//                    JobDetailsEvent.GetJobDetails(
//                        jobID,
//                        LatLng(it.latitude, it.longitude)
//                    )
//                )
//            }
//        }
//        onDispose(locationListener::release)
//    }
//    val showDetailsDialog = remember {
//        mutableStateOf(false)
//    }
//
//    val jobItem = remember {
//        mutableStateOf<DriverJobItem?>(null)
//    }
//
//    viewModel.stateUpdateJobStatus.value.data?.let {
//        LaunchedEffect(Unit) {
//            currentLocation.value?.let {
//                viewModel.getJobs(jobID, LatLng(it.latitude, it.longitude))
//            }
//        }
//    }
//
//    viewModel.jobDetailsState.value.data?.let {
//        LaunchedEffect(key1 = Unit) {
//            jobItem.value = it
//            if (it.jobStatus() == JobStatus.Complete) {
//                onNavigation(
//                    Destinations.UPLOAD_IMAGES,
//                    jobItem.value?.id.toString()
//                )
//            }
//        }
//    }
//
//    AppCompose(
//        state
//    ) {
//        if (jobItem.value?.jobStatus().isValidStatus()) {
//            val currentLatLng = LatLng(
//                currentLocation.value?.latitude ?: 0.0,
//                currentLocation.value?.longitude ?: 0.0
//            )
//            val pickupLatLng = LatLng(
//                (jobItem.value?.locationLat ?: "0").toDoubleOrNull() ?: 0.0,
//                (jobItem.value?.locationLang ?: "0").toDoubleOrNull() ?: 0.0
//            )
//
//            jobItem.value?.let {
//                val cameraPositionState: CameraPositionState = rememberCameraPositionState {
//                    position = CameraPosition.fromLatLngZoom(
//                        currentLatLng, 11f
//                    )
//                }
//                val latLngBounds = remember {
//                    LatLngBounds.Builder()
//                        .include(
//                            currentLatLng
//                        ).include(
//                            pickupLatLng
//                        )
//                }
//
//                Box(Modifier.fillMaxSize()) {
//                    GoogleMap(
//                        cameraPositionState = cameraPositionState, onMapLoaded = {
//                            cameraPositionState.move(
//                                CameraUpdateFactory.newLatLngBounds(
//                                    latLngBounds.build(), 5
//                                )
//                            )
//                        }, properties = MapProperties(
//                            latLngBoundsForCameraTarget = latLngBounds.build(),
//                            mapStyleOptions = MapStyleOptions(
//                                "[\n" + "    {\n" + "        \"featureType\": \"all\",\n" + "        \"elementType\": \"labels.text.fill\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"saturation\": 36\n" + "            },\n" + "            {\n" + "                \"color\": \"#333333\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 40\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"all\",\n" + "        \"elementType\": \"labels.text.stroke\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"visibility\": \"on\"\n" + "            },\n" + "            {\n" + "                \"color\": \"#ffffff\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 16\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"all\",\n" + "        \"elementType\": \"labels.icon\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"visibility\": \"off\"\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"administrative\",\n" + "        \"elementType\": \"geometry.fill\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#fefefe\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 20\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"administrative\",\n" + "        \"elementType\": \"geometry.stroke\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#fefefe\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 17\n" + "            },\n" + "            {\n" + "                \"weight\": 1.2\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"landscape\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#edebe4\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 20\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"poi\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#f5f5f5\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 21\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"poi.park\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#dedede\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 21\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"poi.park\",\n" + "        \"elementType\": \"geometry.fill\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#d1ecc7\"\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"road.highway\",\n" + "        \"elementType\": \"geometry.fill\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#ffffff\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 17\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"road.highway\",\n" + "        \"elementType\": \"geometry.stroke\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#ffffff\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 29\n" + "            },\n" + "            {\n" + "                \"weight\": 0.2\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"road.arterial\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#ffffff\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 18\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"road.local\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#ffffff\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 16\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"transit\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#f2f2f2\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 19\n" + "            }\n" + "        ]\n" + "    },\n" + "    {\n" + "        \"featureType\": \"water\",\n" + "        \"elementType\": \"geometry\",\n" + "        \"stylers\": [\n" + "            {\n" + "                \"color\": \"#bddddd\"\n" + "            },\n" + "            {\n" + "                \"lightness\": 17\n" + "            }\n" + "        ]\n" + "    }\n" + "]"
//                            ),
//                        )
//                    ) {
//
//                        MapMarker(
//                            position = currentLatLng,
//                            title = "Current Location",
//                            iconResourceId = R.drawable.icon_pickup
//                        )
//                        MapMarker(
//                            position = pickupLatLng,
//                            title = "Pickup Location",
//                            iconResourceId = R.drawable.icon_pickup
//                        )
//                        state.locationDirection?.let {
//                            Polyline(
//                                points = it.points ?: emptyList(), color = Color.Blue
//                            )
//                        }
//                    }
//
//                    androidx.compose.animation.AnimatedVisibility(
//                        visible = !showDetailsDialog.value,
//                        modifier = Modifier
//                            .align(Alignment.BottomCenter)
//                            .padding(
//                                start = 20.dp, end = 20.dp, bottom = 20.dp
//                            )
//                    ) {
//                        jobItem.value?.let { jobItem ->
//                            JobInformationCard(
//                                jobItem = jobItem,
//                                modifier = Modifier,
//                                timeArrival = state.locationDirection?.duration
//                                    ?: "---",
//                                distance = state.locationDirection?.distance,
//                                onUpdateClick = {
//                                    if (jobItem.jobStatus() == JobStatus.InProgress) {
////                                        onNavigation(
////                                            Destinations.UPLOAD_IMAGES,
////                                            jobItem.id.toString()
////                                        )
//                                    } else {
//                                        viewModel.updateJobStatus(jobItem.id.toString(), it)
//                                    }
//                                },
//                                onShowInfoClick = {
//                                    showDetailsDialog.value = true
//                                }
//                            )
//                        }
//                    }
//
//                    androidx.compose.animation.AnimatedVisibility(visible = showDetailsDialog.value) {
//                        jobItem.value?.let {
//                            Surface(
//                                Modifier
//                                    .fillMaxSize(), color = Color.Gray.copy(alpha = 0.30f)
//                            ) {
//                                JobDetailsScreen(
//                                    jobItem = it,
//                                    onCloseDialog = { showDetailsDialog.value = false })
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
//    }
//
//
//    BackHandler {
//        if (showDetailsDialog.value) {
//            showDetailsDialog.value = false
//        } else {
//            onBack()
//        }
//    }
//}
//
//fun JobStatus?.isValidStatus(): Boolean {
//    return this != JobStatus.Complete
//            && this != JobStatus.Assigned
//            && this != null
//}
//
//@Composable
//fun MapMarker(
//    position: LatLng, title: String, @DrawableRes iconResourceId: Int,
//) {
//    val context = LocalContext.current
//    val icon = bitmapDescriptorFromVector(
//        context, iconResourceId
//    )
//    Marker(
//        state = MarkerState(position = position),
//        title = title,
//        icon = icon,
//    )
//}
//
//fun bitmapDescriptorFromVector(
//    context: Context, vectorResId: Int,
//): BitmapDescriptor? {
//    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
//    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
//    val bm = Bitmap.createBitmap(
//        drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
//    )
//
//    val canvas = android.graphics.Canvas(bm)
//    drawable.draw(canvas)
//    return BitmapDescriptorFactory.fromBitmap(bm)
//}
//
//
