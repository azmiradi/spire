package com.azmiradi.kotlin_base.domain.models

data class ValidationResult(
    val successful: Boolean = true,
    val errorMessage: Int? = null
)