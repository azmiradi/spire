package com.azmiradi.kotlin_base.data.models

import com.azmiradi.kotlin_base.data.exception.BaseException


// A generic class that contains data and status about loading this data.
sealed class Resource<out Model> {
    data class Progress<Model>(val loading: Boolean, val partialData: Model? = null) :
        Resource<Model>()

    data class Success<out Model>(val model: Model) : Resource<Model>()
    data class Failure(val exception: BaseException) : Resource<Nothing>()

    companion object {
        fun <Model> loading(
            loading: Boolean = true, partialData: Model? = null
        ): Resource<Model> = Progress(loading, partialData)

        fun <Model> success(model: Model): Resource<Model> = Success(model)
        fun <Model> failure(exception: BaseException): Resource<Model> = Failure(exception)
    }
}