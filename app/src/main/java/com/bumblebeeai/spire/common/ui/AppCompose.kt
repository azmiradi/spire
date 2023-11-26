package com.bumblebeeai.spire.common.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.BaseState
import com.azmiradi.kotlin_base.utilities.extensions.typeOf
import com.bumblebeeai.spire.common.domain.model.BaseErrorResponse
import com.bumblebeeai.spire.home.jobs.domain.model.VehicleCheck

@Composable
fun AppCompose(
    baseState: BaseState,
    onVachelCheckIsRequired: ((url: String) -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    Box(Modifier.fillMaxSize()) {
        content()
        LaunchedEffect(key1 = baseState.error) {
            baseState.error?.let {
                val message = when (it) {
                    is BaseException.Client.BodyError -> {
                        try {
                            val errorBody =
                                it.getErrorBody<BaseErrorResponse<VehicleCheck>>(typeOf<BaseErrorResponse<VehicleCheck>>())
                            errorBody?.data?.url?.let {
                                onVachelCheckIsRequired?.invoke(it)
                            }
                            errorBody?.message ?: ""
                        } catch (e: Exception) {
                            e.message
                        }
                    }

                    else -> {
                        it.message ?: ""
                    }
                }
                Log.e("ErrorApp g ", ("${baseState.error}  : "))
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }

        if (baseState.loading) {
            Dialog(onDismissRequest = { }) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

    }
}