package com.azmiradi.android_base.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.azmiradi.kotlin_base.data.models.ViewState

@Composable
fun <Data> AppCompose(
    viewModel: StateViewModel<Data>,
    onSuccessRemoteRequest: (Data) -> Unit,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    Box(Modifier.fillMaxSize()) {
        content()
        when (val data = viewModel.viewState.collectAsState().value) {
            is ViewState.Error -> {
                Toast.makeText(context, data.exception.message, Toast.LENGTH_SHORT).show()
            }

            ViewState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is ViewState.Success -> {
                onSuccessRemoteRequest(data.data)
            }

            null -> {}
        }
    }

    DisposableEffect(true) {
        onDispose {
            viewModel.clearData()
        }
    }
}