package com.bumblebeeai.spire.common.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val TextFiledContainerColor = Color(0xffffffff)
val TitleColor = Color(0xff4D4D4D)
val UnSelectedItemColor = Color(0xff979797)
val SelectedSwitchColor = Color(0xff81A7DE)

private val darkColorScheme = darkColorScheme(
    primary = Color(0xFF3A6EB9),
    secondary = Color(0xFFF9F9F9),
    tertiary = Color(0xFFACB1C0),
    background = Color(0xFFF9F9F9),
    outline = Color(0xffEBECED),
    surfaceVariant = Color(0xffF9F9F9)
)

private val lightColorScheme = lightColorScheme(
    primary = Color(0xFF3A6EB9),
    secondary = Color(0xFFF9F9F9),
    tertiary = Color(0xFFACB1C0),
    background = Color(0xFFF9F9F9),
    outline = Color(0xffEBECED),
    surfaceVariant = Color(0xffF9F9F9)
)

@Composable
fun SpireTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> {
            darkColorScheme
        }

        else -> {
            lightColorScheme
        }
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CustomTypography,
        content = content
    )
}