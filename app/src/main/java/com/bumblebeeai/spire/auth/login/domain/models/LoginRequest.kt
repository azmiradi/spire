package com.bumblebeeai.spire.auth.login.domain.models

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.domain.models.RequestValidation
import com.azmiradi.kotlin_base.utilities.extensions.isPasswordValid
import com.azmiradi.kotlin_base.utilities.extensions.isPhoneNumberValid

internal data class LoginRequest(
    private val phoneNumber: String,
    private val password: String,
) : RequestValidation {
    override fun isValidateInput(): Boolean {
        if (!phoneNumber.isPhoneNumberValid()) {
            throw BaseException.Local.RequestValidation(this::class, "Phone Number Not Valid")
        }

        if (!password.isPasswordValid()) {
            throw BaseException.Local.RequestValidation(this::class, "Password Not Valid")
        }
        return true
    }

}