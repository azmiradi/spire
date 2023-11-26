package com.bumblebeeai.spire.job_details.presentation.manager

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.BaseState
import com.bumblebeeai.spire.home.jobs.data.models.dto.DriverJobItem
import com.bumblebeeai.spire.job_details.domain.models.LocationDirection

class JobDetailsState(
    val updateJobStatus: String? = null,
    val driverJobItem: DriverJobItem? = null,
    val locationDirection: LocationDirection? = null,
    loading: Boolean = false,
    error: BaseException? = null,
) : BaseState(loading, error)
