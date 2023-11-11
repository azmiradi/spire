package com.bumblebeeai.spire.auth.login.presentation.manager

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.BaseState
import com.azmiradi.kotlin_base.domain.models.ValidationResult

class LoginState(
    val phoneError: ValidationResult = ValidationResult(),
    val passwordError: ValidationResult = ValidationResult(),
    val loginSuccess: Boolean = false,
    loading: Boolean = false,
    error: BaseException? = null,
) : BaseState(loading, error)
