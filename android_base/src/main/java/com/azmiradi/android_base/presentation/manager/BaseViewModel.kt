package com.azmiradi.android_base.presentation.manager

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State, Event> : ViewModel() {
    private val _viewState = MutableStateFlow(createInitialState())
    val viewState: StateFlow<State> = _viewState

     open fun onEvent(event: Event) {
        _viewState.value = createInitialState()
    }

    fun emit(state: State) {
        _viewState.value = state
    }

    override fun onCleared() {
        super.onCleared()
        _viewState.value = createInitialState()
    }

    abstract fun createInitialState(): State
}