package com.bumblebeeai.spire.home.jobs.presentation.manager

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.azmiradi.android_base.presentation.manager.BaseViewModel
import com.bumblebeeai.spire.common.domain.model.enums.JobType
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import com.bumblebeeai.spire.home.jobs.domain.usecases.GetJobsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class JobsViewModel @Inject constructor(
    private val getJobsUseCase: GetJobsUseCase,
) : BaseViewModel<PagingData<DriverJob>, JobEvent>() {
    override fun onEvent(event: JobEvent) {
        super.onEvent(event)
        when (event) {
            is JobEvent.GetJobs -> {
                getJobs(event.jobType)
            }
        }
    }

    private fun getJobs(jobType: JobType) {
        getJobsUseCase.invoke(jobType)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .onEach {
                emit(it)
            }.launchIn(viewModelScope)
    }

    override fun createInitialState(): PagingData<DriverJob> {
        return PagingData.empty()
    }
}