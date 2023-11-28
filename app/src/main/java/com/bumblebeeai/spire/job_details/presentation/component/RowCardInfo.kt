package com.bumblebeeai.spire.job_details.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumblebeeai.spire.R

@Composable
fun RowCardInfo(
    title: String ,
    value: String,
    icon: Int ,
    sideIcon: Int? = null,
    sideIconAction: (() -> Unit)? = null,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id =icon),
            contentDescription = "image description",
            modifier = Modifier
                .padding(1.dp)
                .width(15.6.dp)
                .height(16.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF979797),
                    )
                )
                Text(
                    text = value,
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF4D4D4D),
                    )
                )
            }

            sideIcon?.let {
                Image(painter = painterResource(id = sideIcon),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        sideIconAction?.invoke()
                    })
            }
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
}