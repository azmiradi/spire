package com.azmiradi.kotlin_base.data.models

import com.azmiradi.kotlin_base.data.exception.BaseException

open class BaseState(
    val loading: Boolean = false,
    val error: BaseException?= null,
)