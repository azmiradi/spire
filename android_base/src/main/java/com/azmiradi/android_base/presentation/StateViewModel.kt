package com.azmiradi.android_base.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azmiradi.kotlin_base.data.models.Resource
import com.azmiradi.kotlin_base.data.models.ViewState
import com.azmiradi.kotlin_base.domain.mapper.mapToViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StateViewModel<Data> : ViewModel() {

    private val _viewState: MutableStateFlow<ViewState<Data>?> = MutableStateFlow(null)
    val viewState: StateFlow<ViewState<Data>?> = _viewState

    fun emitData(dataResult: Flow<Resource<Data>>) {
        viewModelScope.launch(Dispatchers.Main) {
            dataResult.mapToViewState().collect {
                _viewState.emit(it)
            }
        }
    }

    fun clearData() {
        viewModelScope.launch(Dispatchers.Main) {
            _viewState.emit(null)
        }
    }

    override fun onCleared() {
        super.onCleared()
        clearData()
    }
}