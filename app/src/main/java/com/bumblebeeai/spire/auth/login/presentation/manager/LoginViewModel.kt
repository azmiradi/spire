package com.bumblebeeai.spire.auth.login.presentation.manager

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validatorUseCase: ValidatorUseCase,
) : BaseViewModel<LoginState, LoginEvent>() {
    private val _viewState = mutableStateOf(LoginState())
    val viewState: State<LoginState> = _viewState

    private val phoneValidationRules = listOf(
        ValidationRules(pattern = PHONE_PATTERN, R.string.phone_invalid)
    )
    private val passwordValidationRules = listOf(
        ValidationRules(pattern = PASSWORD_PATTERN, R.string.password_invalid)
    )

    override fun onEvent(event: LoginEvent) {
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

            is LoginEvent.SubmitToLogin -> login(event.loginRequest)

            is LoginEvent.SaveLoginData -> saveLoginData(event.loginRequest)
        }
    }

    private fun saveLoginData(loginRequest: LoginRequest) {

    }

    private fun loginByBiometric() {
        
    }

    private fun login(loginRequest: LoginRequest) {
        loginUseCase.invoke(loginRequest).map { data ->
            when (data) {
                is Resource.Failure -> LoginState(error = data.exception)
                is Resource.Progress -> LoginState(loading = data.loading)
                is Resource.Success -> LoginState(loginSuccess = true)
            }
        }.onEach {
            emit(it)
        }.launchIn(viewModelScope)
    }

    override fun emit(state: LoginState) {
        _viewState.value = state
    }

}