package com.bumblebeeai.spire.job_details.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumblebeeai.spire.R


@Preview
@Composable
fun CompleteJobDialog() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(Color(0xE5FFFFFF)),
        shape = RoundedCornerShape(size = 7.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = "Great Job!",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF4D4D4D),
                )
            )
            Spacer(modifier = Modifier.height(17.dp))

            Box(
                Modifier
                    .width(165.dp)
                    .height(165.dp)
                    .padding(
                        start = 14.10712.dp,
                        top = 13.75.dp,
                        end = 13.75001.dp,
                        bottom = 13.75.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.Center)
                )
                Image(
                    painter = painterResource(id = R.drawable.correct),
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(17.dp))


            Text(
                text = "Thank you for completing the task\nKeep up the good work ;)",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF4D4D4D),
                    textAlign = TextAlign.Center,
                )
            )

            Spacer(modifier = Modifier.height(34.dp))

            Button(
                onClick = {

                },
                Modifier
                    .width(126.dp)
                    .height(35.dp),
                shape = RoundedCornerShape(size = 90.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF3A6EB9))
            ) {
                Text(text = "", color = Color.White)
            }
            Spacer(modifier = Modifier.height(34.dp))

        }
    }

}