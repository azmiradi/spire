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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.app_entry_point.navigation.presentation.screens.AppNavigationRouts.Companion.HOME
import com.bumblebeeai.spire.app_entry_point.navigation.presentation.screens.AppNavigationRouts.Companion.LOGIN
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest
import com.bumblebeeai.spire.auth.login.presentation.component.PasswordTextFiled
import com.bumblebeeai.spire.auth.login.presentation.component.PhoneNumberTextFiled
import com.bumblebeeai.spire.auth.login.presentation.manager.LoginEvent
import com.bumblebeeai.spire.auth.login.presentation.manager.LoginViewModel
import com.bumblebeeai.spire.common.ui.AppCompose
import com.bumblebeeai.spire.common.ui.theme.CustomTypography
import com.bumblebeeai.spire.common.ui.theme.TitleColor

@Composable
internal fun LoginScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<LoginViewModel>()
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

    val state = viewModel.viewState.collectAsState().value

    LaunchedEffect(key1 = state.loginSuccess) {
        if (state.loginSuccess) {
            navController.navigate(HOME) {
                popUpTo(LOGIN) {
                    inclusive = true
                }
            }
        }
    }

    AppCompose(
        baseState = state,
        content = {
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
                        var phoneNumber by rememberSaveable { mutableStateOf("") }
                        var password by rememberSaveable { mutableStateOf("") }
                        Spacer(modifier = Modifier.height(33.dp))

                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = stringResource(id = R.string.logo),
                            modifier = Modifier.size(width = 175.dp, height = 50.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = stringResource(id = R.string.welcome_to_spire),
                            color = TitleColor
                        )

                        Spacer(modifier = Modifier.height(26.dp))

                        PhoneNumberTextFiled(error = !(state.phoneError.successful)) {
                            viewModel.onEvent(LoginEvent.PhoneChanged(it))
                            phoneNumber = it
                        }

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
                            style = CustomTypography.labelSmall.copy(
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
                                        mobile = phoneNumber,
                                        password = password
                                    )
                                )
                            )
                        }) {
                            Text(
                                text = stringResource(id = R.string.login).uppercase(),
                                style = CustomTypography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(700)
                                ),
                            )
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        ElevatedButton(
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent
                            ),
                            contentPadding = PaddingValues(0.dp),
                            onClick = {

                            },
                            elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
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