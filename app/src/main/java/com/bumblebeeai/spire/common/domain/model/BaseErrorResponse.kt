package com.bumblebeeai.spire.common.domain.model

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse<Model>(
	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("errors")
	val errors: Errors? = null,

	@field:SerializedName("data")
	val data: Model? = null,
)
data class Errors(
	@field:SerializedName("mobile")
	val mobile: List<String?>? = null
)
