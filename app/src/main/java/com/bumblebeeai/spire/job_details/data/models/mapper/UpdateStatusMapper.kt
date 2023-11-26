package com.bumblebeeai.spire.job_details.data.models.mapper

import com.azmiradi.kotlin_base.domain.mapper.Mapper
import com.bumblebeeai.spire.job_details.data.models.dto.UpdateStatusJobDto

object UpdateStatusMapper : Mapper<UpdateStatusJobDto, String>() {
    override fun dtoToDomain(model: UpdateStatusJobDto): String {
        return model.message ?: ""
    }
}