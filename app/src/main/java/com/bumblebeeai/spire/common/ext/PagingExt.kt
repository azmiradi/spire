package com.bumblebeeai.spire.common.ext

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.BaseState


internal fun <T : Any> LazyPagingItems<T>.convertToBaseState(): BaseState {
    return this.run {
        when {
            loadState.refresh is LoadState.Loading -> {
                BaseState(loading = true)
            }

            loadState.refresh is LoadState.Error -> {
                BaseState(
                    error = BaseException.Client.BodyError(
                        0,
                        (this.loadState.refresh as LoadState.Error).error.message
                    )
                )
            }

            loadState.append is LoadState.Loading -> {
                BaseState(loading = true)
            }

            loadState.append is LoadState.Error -> {
                BaseState(
                    error = BaseException.Client.BodyError(
                        0,
                        (this.loadState.append as LoadState.Error).error.message
                    )
                )
            }

            else -> {
                BaseState()
            }
        }
    }
}