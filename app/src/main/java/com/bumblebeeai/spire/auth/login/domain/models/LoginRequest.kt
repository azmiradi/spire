package com.bumblebeeai.spire.auth.login.domain.models

import com.google.errorprone.annotations.Keep
@Keep
internal data class LoginRequest(
     val mobile: String,
     val password: String,
)