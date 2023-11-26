package com.bumblebeeai.spire.home.jobs.data.mapper

import com.azmiradi.kotlin_base.domain.mapper.Mapper
import com.azmiradi.kotlin_base.utilities.constants.DEFAULT_STRING_VALUE
import com.azmiradi.kotlin_base.utilities.constants.ISO8601_FORMAT
import com.azmiradi.kotlin_base.utilities.constants.SHORT_DATE_FORMAT
import com.azmiradi.kotlin_base.utilities.extensions.formatDate
import com.bumblebeeai.spire.home.jobs.data.models.dto.DriverJobDto
import com.bumblebeeai.spire.home.jobs.data.models.dto.DriverJobItem
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob

internal object DriverJobMapper : Mapper<DriverJobItem, DriverJob>() {
    override fun dtoToDomain(model: DriverJobItem): DriverJob {
        return DriverJob(
            jobDetails = "Vehicle Rolling: ${if (model.rolling == 1) "Yes" else "No"}" +
                    " / Service Type: ${model.service?.type ?: "---"} / ETA:${
                        model.eta?.split("-")?.getOrNull(0)?.plus(":00") ?: DEFAULT_STRING_VALUE
                    } / Fault ${model.fault ?: DEFAULT_STRING_VALUE}",
            locationAddress = model.vehicleAddress ?: DEFAULT_STRING_VALUE,
            date = model.createdAt?.formatDate(ISO8601_FORMAT, SHORT_DATE_FORMAT)
                ?: DEFAULT_STRING_VALUE,
            jobID = model.id ?: 0
        )
    }
}