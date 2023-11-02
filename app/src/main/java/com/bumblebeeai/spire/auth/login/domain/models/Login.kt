package com.bumblebeeai.spire.auth.login.domain.models

data class Login (
    val accessToken: String?= null,
    val lastName: String? = null,
    val merchantId: String? = null,
    val tokenType: String? = null,
    val firstName: String? = null
)