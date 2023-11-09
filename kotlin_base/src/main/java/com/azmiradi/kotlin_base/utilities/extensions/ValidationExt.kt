package com.azmiradi.kotlin_base.utilities.extensions

fun String.isPhoneNumberValid(): Boolean {
    val pattern = "^(\\+\\d{1,4}[- ]?)?\\d{10}$"
    return matches(Regex(pattern))
}

fun String.isPasswordValid(): Boolean {
    val pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$"
    return matches(Regex(pattern))
}