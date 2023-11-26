package com.bumblebeeai.spire.app_entry_point.navigation.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.common.ui.SetBarsColor

@Composable
fun SplashScreen() {
    SetBarsColor(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primary)
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier
                .align(Center)
                .width(210.dp)
                .height(60.dp)
        )
    }
}