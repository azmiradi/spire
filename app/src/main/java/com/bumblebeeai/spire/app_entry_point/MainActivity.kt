package com.bumblebeeai.spire.app_entry_point

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.bumblebeeai.spire.app_entry_point.navigation.presentation.screens.NavigationScreensController
import com.bumblebeeai.spire.common.ui.theme.SpireTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpireTheme {
                NavigationScreensController()
            }
        }
    }
}

