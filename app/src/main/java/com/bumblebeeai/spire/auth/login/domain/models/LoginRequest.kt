package com.bumblebeeai.spire.auth.login.domain.models

internal data class LoginRequest(
    private val phoneNumber: String,
    private val password: String,
)