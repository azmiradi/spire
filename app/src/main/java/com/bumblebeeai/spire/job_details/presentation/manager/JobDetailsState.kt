package com.bumblebeeai.spire.job_details.presentation.manager

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.BaseState
import com.bumblebeeai.spire.home.jobs.data.models.dto.DriverJobItemDto
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import com.bumblebeeai.spire.job_details.domain.models.LocationDirection

internal class JobDetailsState(
    val updateJobStatus: String? = null,
    val driverJob: DriverJob? = null,
    val locationDirection: LocationDirection? = null,
    loading: Boolean = false,
    error: BaseException? = null,
) : BaseState(loading, error)
