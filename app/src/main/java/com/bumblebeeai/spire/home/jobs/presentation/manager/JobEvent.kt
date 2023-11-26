package com.bumblebeeai.spire.home.jobs.presentation.manager

import com.bumblebeeai.spire.common.domain.model.enums.JobType

internal sealed class JobEvent(jobType: JobType) {
    data class GetJobs(val jobType: JobType) : JobEvent(jobType)
}