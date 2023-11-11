package com.azmiradi.kotlin_base.domain.usecases

import com.azmiradi.kotlin_base.domain.models.ValidationResult

data class ValidationRules(
    val pattern: String,
    val message: Int,
)

class ValidatorUseCase {
    operator fun invoke(
        data: String,
        validationList: List<ValidationRules>,
    ): ValidationResult {
        for (i in validationList.indices) {
            if (!data.matches(Regex(validationList[i].pattern))) {
                return ValidationResult(successful = false, validationList[i].message)
            }
        }
        return ValidationResult(successful = true)
    }
}