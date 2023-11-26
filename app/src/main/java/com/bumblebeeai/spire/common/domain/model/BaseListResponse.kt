package com.bumblebeeai.spire.common.domain.model

import com.google.gson.annotations.SerializedName

data class BaseListResponse<Model>(
    @field:SerializedName("per_page")
    val perPage: Int? = null,

    @field:SerializedName("data")
    val data: List<Model?>? = null,

    @field:SerializedName("last_page")
    val lastPage: Int? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("from")
    val from: Int? = null,

    @field:SerializedName("to")
    val to: Int? = null,

    @field:SerializedName("current_page")
    val currentPage: Int? = null
)