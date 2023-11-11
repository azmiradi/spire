package com.bumblebeeai.spire.auth.login.presentation.manager

import com.bumblebeeai.spire.auth.login.domain.models.LoginRequest

internal sealed class LoginEvent(loginRequest: LoginRequest? = null) {
    data class PhoneChanged(val phone: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data class SubmitToLogin(val loginRequest: LoginRequest) : LoginEvent(loginRequest)
    data class SaveLoginData(val loginRequest: LoginRequest) : LoginEvent(loginRequest)
}