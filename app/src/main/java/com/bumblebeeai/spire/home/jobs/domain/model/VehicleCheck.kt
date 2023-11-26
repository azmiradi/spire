package com.bumblebeeai.spire.home.jobs.domain.model

import com.google.errorprone.annotations.Keep


@Keep
data class VehicleCheck(
	val required: Boolean? = null,
	val url: String? = null,
)
