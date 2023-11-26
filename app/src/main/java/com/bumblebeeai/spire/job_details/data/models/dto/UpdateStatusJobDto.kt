package com.bumblebeeai.spire.job_details.data.models.dto

import com.google.gson.annotations.SerializedName

data class UpdateStatusJobDto(
	@field:SerializedName("message")
	val message: String? = null
)
