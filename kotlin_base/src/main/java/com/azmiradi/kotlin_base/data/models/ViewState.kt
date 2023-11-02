package com.azmiradi.kotlin_base.data.models

import com.azmiradi.kotlin_base.data.exception.BaseException

sealed class ViewState<out T> {
    data object Loading: ViewState<Nothing>()
    data class Success<out T>(val data: T) : ViewState<T>()
    data class Error(val exception: BaseException) : ViewState<Nothing>()
}
