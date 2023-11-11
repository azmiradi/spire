package com.azmiradi.android_base.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.azmiradi.kotlin_base.data.models.BaseState

@Composable
fun AppCompose(
    baseState: BaseState,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    Box(Modifier.fillMaxSize()) {
        content()
        baseState.error?.let {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }

        if (baseState.loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}