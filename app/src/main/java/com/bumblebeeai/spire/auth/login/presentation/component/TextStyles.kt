package com.bumblebeeai.spire.auth.login.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bumblebeeai.spire.common.ui.theme.CustomTypography

@Composable
fun PlaceHolderText(text: String) {
    Text(
        text = text, style = placeHolderStyle
    )
}

val placeHolderStyle = CustomTypography.labelSmall.copy(
    color = Color(0xffC3C7D2),
    fontSize = 14.sp,
    fontWeight = FontWeight(300)
)