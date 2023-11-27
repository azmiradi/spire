package com.bumblebeeai.spire.home.jobs.presentation.manager

import androidx.paging.PagingData
import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.BaseState
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob

internal class JobsState(
    val jobs: PagingData<DriverJob>? = null,
    loading: Boolean = false,
    error: BaseException? = null,
) : BaseState(loading, error)
