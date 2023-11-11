package com.bumblebeeai.spire.auth.login.presentation.screens

import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azmiradi.android_base.presentation.AppCompose
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest
import com.bumblebeeai.spire.auth.login.presentation.component.PasswordTextFiled
import com.bumblebeeai.spire.auth.login.presentation.component.PlaceHolderText
import com.bumblebeeai.spire.auth.login.presentation.manager.LoginEvent
import com.bumblebeeai.spire.auth.login.presentation.manager.LoginViewModel
import com.bumblebeeai.spire.common.ui.theme.TextFiledContainerColor
import com.bumblebeeai.spire.common.ui.theme.TitleColor
import com.bumblebeeai.spire.common.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
internal fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current

    val biometricPrompt = remember {
        context.createBiometricPrompt(onAuthenticationSucceeded = {

        }, onAuthenticationFailed = {

        }, onAuthenticationError = {

        })
    }

    val promptInfo = remember {
        createPromptInfo()
    }

    val state = viewModel.viewState.value

    AppCompose(baseState = state, content = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
        ) {
            Card(
                shape = RoundedCornerShape(11.dp),
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(end = 14.dp, start = 14.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp, end = 18.dp)
                ) {
                    var phone by rememberSaveable { mutableStateOf("") }
                    var password by rememberSaveable { mutableStateOf("") }

                    Spacer(modifier = Modifier.height(33.dp))

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = stringResource(id = R.string.logo),
                        modifier = Modifier.size(width = 175.dp, height = 50.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = stringResource(id = R.string.welcome_to_spire), color = TitleColor
                    )

                    Spacer(modifier = Modifier.height(26.dp))

                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                            viewModel.onEvent(LoginEvent.PhoneChanged(phone))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = TextFiledContainerColor),
                        shape = RoundedCornerShape(10.dp),
                        singleLine = true,
                        placeholder = {
                            PlaceHolderText(stringResource(id = R.string.phone))
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        isError = !state.phoneError.successful
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    PasswordTextFiled(
                        onPasswordChange = {
                            password = it
                            viewModel.onEvent(LoginEvent.PasswordChanged(password))
                        }, isError = !state.passwordError.successful
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.forget_password),
                        style = Typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 13.sp,
                            fontWeight = FontWeight(400)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                            .clickable {

                            },
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    Button(modifier = Modifier.fillMaxWidth(), onClick = {
                        viewModel.onEvent(
                            LoginEvent.SubmitToLogin(
                                LoginRequest(
                                    phoneNumber = phone,
                                    password = password
                                )
                            )
                        )
                    }) {
                        Text(
                            text = stringResource(id = R.string.login).uppercase(),
                            style = Typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700)
                            ),
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    ElevatedButton(
                        shape = CircleShape, colors = ButtonDefaults.buttonColors(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent
                        ), contentPadding = PaddingValues(0.dp), onClick = {

                        }, elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.biomatric),
                            contentDescription = ""
                        )
                    }
                    Spacer(modifier = Modifier.height(43.dp))
                }
            }
        }
    })
}


private fun Context.createBiometricPrompt(
    onAuthenticationSucceeded: () -> Unit,
    onAuthenticationFailed: () -> Unit,
    onAuthenticationError: (errString: String) -> Unit,
): BiometricPrompt {
    val executor = ContextCompat.getMainExecutor(this)

    val callback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) =
            onAuthenticationError(errString.toString())

        override fun onAuthenticationFailed() = onAuthenticationFailed()
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            onAuthenticationSucceeded()
        }
    }

    return BiometricPrompt(this as FragmentActivity, executor, callback)
}


private fun createPromptInfo(): BiometricPrompt.PromptInfo {
    return BiometricPrompt.PromptInfo.Builder().setTitle("hi").setSubtitle("hi")
        .setDescription("hi").setConfirmationRequired(false).setNegativeButtonText("hi")
        // .setDeviceCredentialAllowed(true)
        .build()
}