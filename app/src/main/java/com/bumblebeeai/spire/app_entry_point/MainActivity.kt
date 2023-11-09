package com.bumblebeeai.spire.app_entry_point

import android.app.KeyguardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.auth.login.presentation.screens.LoginScreen
import com.bumblebeeai.spire.common.ui.theme.SpireTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executor
import java.util.concurrent.Executors



@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (keyguardManager.isDeviceSecure) {
            // The device is secured with a lock screen and user authentication is required.

            // Attempt to access the key or perform the cryptographic operation.
        } else {
            // The device is not secured, or the user has not authenticated yet.
            // Prompt the user to unlock the device or set up security.
        }
        setContent {
            SpireTheme {
                LoginScreen()

            }
        }


    }



}

