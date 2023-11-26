package com.bumblebeeai.spire.common.domain.model

import com.google.gson.annotations.SerializedName

data class BaseDataResponse<Model>(
    @field:SerializedName("data")
    val data: Model? = null,
)