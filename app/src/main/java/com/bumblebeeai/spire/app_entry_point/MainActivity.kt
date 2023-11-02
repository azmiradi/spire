package com.bumblebeeai.spire.app_entry_point

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bumblebeeai.spire.common.ui.theme.SpireTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("packageName : $packageName")
        setContent {
            SpireTheme {

            }
        }
    }
}
