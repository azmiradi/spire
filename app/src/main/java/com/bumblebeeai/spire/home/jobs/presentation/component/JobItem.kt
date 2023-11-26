package com.bumblebeeai.spire.home.jobs.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.common.ui.theme.CustomTypography
import com.bumblebeeai.spire.common.ui.theme.TitleColor
import com.bumblebeeai.spire.common.ui.theme.UnSelectedItemColor
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobItem(driverJob: DriverJob, onClick:()->Unit) {
    Card(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(size = 10.8.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(
            contentColor = Color.White
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(0.5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = driverJob.date,
                    style = CustomTypography.titleLarge.copy(
                        fontSize = 12.sp,
                        color = TitleColor,
                    )
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.truck),
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "5 Miles Away",
                        style = CustomTypography.titleLarge.copy(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.icon_location),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = "Location",
                        style = CustomTypography.titleLarge.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = UnSelectedItemColor,
                        )
                    )

                    Text(
                        text = driverJob.locationAddress,
                        style = CustomTypography.titleLarge.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = TitleColor
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.file),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = "Job Details",
                        style = CustomTypography.titleLarge.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = UnSelectedItemColor,
                        )
                    )

                    Text(
                        text = driverJob.jobDetails,
                        style = CustomTypography.titleLarge.copy(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = TitleColor
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
