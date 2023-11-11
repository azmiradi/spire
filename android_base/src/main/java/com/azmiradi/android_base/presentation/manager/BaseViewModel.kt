package com.azmiradi.android_base.presentation.manager

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<State,Event>: ViewModel() {
    abstract fun onEvent(event: Event)
    abstract fun emit(state: State)
}