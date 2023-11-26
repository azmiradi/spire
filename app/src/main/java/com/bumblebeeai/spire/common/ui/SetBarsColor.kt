package com.bumblebeeai.spire.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetBarsColor(navigationBarColor: Color, statusBarColor: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(
        color = navigationBarColor
    )
    systemUiController.setStatusBarColor(
        color = statusBarColor
    )
}