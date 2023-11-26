package com.azmiradi.kotlin_base.data.models

import com.azmiradi.kotlin_base.data.exception.BaseException

sealed class Resource<out Model> {
    data object Loading : Resource<Nothing>()
    data class Success<out Model>(val model: Model) : Resource<Model>()
    data class Failure(val exception: BaseException) : Resource<Nothing>()
}