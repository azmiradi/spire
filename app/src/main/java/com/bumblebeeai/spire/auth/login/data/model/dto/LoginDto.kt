package com.bumblebeeai.spire.auth.login.data.model.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
	@SerializedName("access_token")
	val accessToken: String? = null,

	@SerializedName("last_name")
	val lastName: String? = null,

	@SerializedName("merchant_id")
	val merchantId: String? = null,

	@SerializedName("token_type")
	val tokenType: String? = null,

	@SerializedName("first_name")
	val firstName: String? = null,
)
