package com.bumblebeeai.spire.auth.login.data.model.dto

import kotlinx.serialization.SerialName

data class LoginDto(
	@SerialName("access_token")
	val accessToken: String? = null,

	@SerialName("last_name")
	val lastName: String? = null,

	@SerialName("merchant_id")
	val merchantId: String? = null,

	@SerialName("token_type")
	val tokenType: String? = null,

	@SerialName("first_name")
	val firstName: String? = null,
)
