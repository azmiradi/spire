package com.bumblebeeai.spire.auth.login.presentation.manager

import androidx.lifecycle.viewModelScope
import com.azmiradi.android_base.presentation.manager.BaseViewModel
import com.azmiradi.kotlin_base.data.models.Resource
import com.azmiradi.kotlin_base.domain.usecases.ValidationRules
import com.azmiradi.kotlin_base.domain.usecases.ValidatorUseCase
import com.azmiradi.kotlin_base.utilities.extensions.PASSWORD_PATTERN
import com.azmiradi.kotlin_base.utilities.extensions.PHONE_PATTERN
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest
import com.bumblebeeai.spire.auth.login.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validatorUseCase: ValidatorUseCase,
) : BaseViewModel<LoginState, LoginEvent>() {

    private val phoneValidationRules = listOf(
        ValidationRules(pattern = PHONE_PATTERN, R.string.phone_invalid)
    )

    private val passwordValidationRules = listOf(
        ValidationRules(pattern = PASSWORD_PATTERN, R.string.password_invalid)
    )

    override fun onEvent(event: LoginEvent) {
        super.onEvent(event)

        when (event) {
            is LoginEvent.PhoneChanged -> {
                val result = validatorUseCase(
                    event.phone, phoneValidationRules
                )
                emit(LoginState(phoneError = result))
            }

            is LoginEvent.PasswordChanged -> {
                val result = validatorUseCase(
                    event.password, passwordValidationRules
                )
                emit(LoginState(passwordError = result))
            }

            is LoginEvent.SubmitToLogin -> {
                login(event.loginRequest)
            }

            is LoginEvent.SaveLoginData -> saveLoginData(event.loginRequest)
        }
    }

    private fun saveLoginData(loginRequest: LoginRequest) {

    }

    private fun login(loginRequest: LoginRequest) {
        if (validInputs(loginRequest))
            loginUseCase.invoke(loginRequest).onEach {
                val result = when (it) {
                    is Resource.Failure -> LoginState(error = it.exception)
                    is Resource.Loading -> LoginState(loading = true)
                    is Resource.Success -> LoginState(loginSuccess = true)
                }
                emit(result)
            }.launchIn(viewModelScope)
    }

    private fun validInputs(loginRequest: LoginRequest): Boolean {
        val validPhone = validatorUseCase(
            loginRequest.mobile, phoneValidationRules
        )

        val validPassword = validatorUseCase(
            loginRequest.password, passwordValidationRules
        )

        if (validPassword.successful && validPhone.successful)
            return true

        emit(LoginState(passwordError = validPassword, phoneError = validPhone))

        return false
    }

    override fun createInitialState() = LoginState()
}