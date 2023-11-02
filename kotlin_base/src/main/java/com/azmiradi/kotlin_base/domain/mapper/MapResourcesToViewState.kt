package com.azmiradi.kotlin_base.domain.mapper

import com.azmiradi.kotlin_base.data.models.Resource
import com.azmiradi.kotlin_base.data.models.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <Model> Flow<Resource<Model>>.mapToViewState(): Flow<ViewState<Model>> {
   return this.map { resource ->
        when (resource) {
            is Resource.Failure -> ViewState.Error(resource.exception)
            is Resource.Progress -> ViewState.Loading
            is Resource.Success -> ViewState.Success(resource.model)
        }
    }
}