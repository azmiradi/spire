package com.bumblebeeai.spire.job_details.presentation.component

import android.Manifest
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.azmiradi.kotlin_base.utilities.constants.DEFAULT_STRING_VALUE
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.common.ext.callNumber
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import com.bumblebeeai.spire.home.jobs.domain.model.enums.JobStatus

@Composable
internal fun JobInformationCard(
    jobItem: DriverJob,
    modifier: Modifier,
    timeArrival: String?,
    distance: String?,
    onUpdateClick: () -> Unit,
) {
    val context = LocalContext.current

    val permissionsRequest = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(
                    context as Activity,
                    Manifest.permission.CALL_PHONE
                )
            ) {
                // context.showSettingDialog()
            } else {
                Toast.makeText(
                    context,
                    "Please allow Call Permissions",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            jobItem.contact?.let {
                context.callNumber(it)
                return@rememberLauncherForActivityResult
            }

            Toast
                .makeText(context, "Phone Number Not Valid", Toast.LENGTH_SHORT)
                .show()
        }
    }

    Card(
        modifier,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Job Details",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF4D4D4D),
                        textAlign = TextAlign.Start,
                    )
                )

                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.truck),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Column {
                        Text(
                            text = "${distance ?: "--"} Miles Away",
                            style = TextStyle(
                                fontSize = 10.8.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF3A6EB9),
                            )
                        )

                        Text(
                            text = jobItem.date,
                            style = TextStyle(
                                fontSize = 10.8.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF969696),
                            )
                        )
                    }
                }

            }

            JobInformationCard(jobItem)

            Button(
                onClick = onUpdateClick, modifier = Modifier
                    .width(296.dp)
                    .height(35.dp)
                    .align(CenterHorizontally),
                shape = RoundedCornerShape(size = 90.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF3A6EB9))
            ) {
                Text(
                    text = jobItem.status.getNextStatus(jobItem.isSinglePoint())?.title ?: "",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(13.dp))
        }
    }
}

@Composable
private fun JobInformationCard(
    jobItem: DriverJob,
) {
    when (jobItem.status) {
        JobStatus.Assigned -> {
            RowCardInfo(
                title = "Vehicle Location",
                value = jobItem.vehicleLocation.address,
                icon = R.drawable.icon_pickup2
            )

            RowCardInfo(
                title = "Destination",
                value = jobItem.destinationLocation?.address ?: DEFAULT_STRING_VALUE,
                icon = R.drawable.icon_pickup2
            )

            RowCardInfo(
                title = "Vehicle Details",
                value = jobItem.vehicleDetails,
                icon = R.drawable.file
            )

            RowCardInfo(
                title = "Job Details",
                value = jobItem.jobDetails,
                icon = R.drawable.file
            )
        }
        JobStatus.Accepted -> {
            RowCardInfo(
                title = "Vehicle Location",
                value = jobItem.vehicleLocation.address,
                icon = R.drawable.icon_pickup2,
                sideIcon = R.drawable.btn_map,
                sideIconAction = {

                }
            )

            RowCardInfo(
                title = "Customer Details",
                value = jobItem.destinationLocation?.address ?: DEFAULT_STRING_VALUE,
                icon = R.drawable.icon_user,
                sideIcon = R.drawable.btn_call,
                sideIconAction = {

                }
            )

            RowCardInfo(
                title = "Vehicle Details",
                value = jobItem.vehicleDetails,
                icon = R.drawable.file
            )

            RowCardInfo(
                title = "Job Details",
                value = jobItem.jobDetails,
                icon = R.drawable.file,
                sideIcon = R.drawable.call_center,
                sideIconAction = {

                }
            )
        }
        JobStatus.OnTheWayLocation -> {
            RowCardInfo(
                title = "Vehicle Location",
                value = jobItem.vehicleLocation.address,
                icon = R.drawable.icon_pickup2,
                sideIcon = R.drawable.btn_map,
                sideIconAction = {

                }
            )

            RowCardInfo(
                title = "Customer Details",
                value = jobItem.destinationLocation?.address ?: DEFAULT_STRING_VALUE,
                icon = R.drawable.icon_user,
                sideIcon = R.drawable.btn_call,
                sideIconAction = {

                }
            )

            RowCardInfo(
                title = "Vehicle Details",
                value = jobItem.vehicleDetails,
                icon = R.drawable.file
            )

            RowCardInfo(
                title = "Job Details",
                value = jobItem.jobDetails,
                icon = R.drawable.file,
                sideIcon = R.drawable.call_center,
                sideIconAction = {

                }
            )
        }
        JobStatus.ArrivedLocation -> {

        }
        JobStatus.CompletedLocation -> {
            RowCardInfo(
                title = "Destination",
                value = jobItem.vehicleLocation.address,
                icon = R.drawable.icon_pickup,
                sideIcon = R.drawable.btn_map,
                sideIconAction = {

                }
            )

            RowCardInfo(
                title = "Contact Person Details",
                value = jobItem.destinationLocation?.address ?: DEFAULT_STRING_VALUE,
                icon = R.drawable.icon_user,
                sideIcon = R.drawable.btn_call,
                sideIconAction = {

                }
            )

            RowCardInfo(
                title = "Vehicle Details",
                value = jobItem.vehicleDetails,
                icon = R.drawable.file
            )

            RowCardInfo(
                title = "Job Details",
                value = jobItem.jobDetails,
                icon = R.drawable.file,
                sideIcon = R.drawable.call_center,
                sideIconAction = {

                }
            )
        }
        JobStatus.OnTheWayDestination -> {
            RowCardInfo(
                title = "Destination",
                value = jobItem.vehicleLocation.address,
                icon = R.drawable.icon_pickup,
                sideIcon = R.drawable.btn_map,
                sideIconAction = {

                }
            )

            RowCardInfo(
                title = "Contact Person Details",
                value = jobItem.destinationLocation?.address ?: DEFAULT_STRING_VALUE,
                icon = R.drawable.icon_user,
                sideIcon = R.drawable.btn_call,
                sideIconAction = {

                }
            )

            RowCardInfo(
                title = "Vehicle Details",
                value = jobItem.vehicleDetails,
                icon = R.drawable.file
            )

            RowCardInfo(
                title = "Job Details",
                value = jobItem.jobDetails,
                icon = R.drawable.file,
                sideIcon = R.drawable.call_center,
                sideIconAction = {

                }
            )
        }
        JobStatus.ArrivedDestination -> {

        }
        JobStatus.Completed -> {

        }
    }
}