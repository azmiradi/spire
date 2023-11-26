package com.bumblebeeai.spire.app_entry_point.navigation.presentation.manager

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azmiradi.kotlin_base.data.models.Resource
import com.bumblebeeai.spire.app_entry_point.navigation.domain.usecases.IsLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class NavigationState(val isLogin: Boolean = false) {
    class IsLogin(isLogin: Boolean) : NavigationState(isLogin = isLogin)
    data object Init : NavigationState()
}

@HiltViewModel
internal class NavigationScreensControllerViewModel @Inject constructor(
    isLoginUseCase: IsLoginUseCase,
) : ViewModel() {
    private val _isLogin = mutableStateOf<NavigationState>(NavigationState.Init)
    val isLogin: MutableState<NavigationState> = _isLogin

    init {
        viewModelScope.launch {
            delay(3000)
            isLoginUseCase().onEach { result ->
                when (result) {
                    is Resource.Failure -> {
                        Log.e("Failure", result.exception.message?:"")
                        _isLogin.value = NavigationState.IsLogin(false)
                    }
                    Resource.Loading -> {}
                    is Resource.Success -> _isLogin.value = NavigationState.IsLogin(result.model)
                }

            }.launchIn(viewModelScope)
        }
    }
}