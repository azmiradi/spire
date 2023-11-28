package com.bumblebeeai.spire.home.jobs.data.mapper

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.domain.mapper.Mapper
import com.azmiradi.kotlin_base.utilities.constants.DEFAULT_STRING_VALUE
import com.azmiradi.kotlin_base.utilities.constants.ISO8601_FORMAT
import com.azmiradi.kotlin_base.utilities.constants.SHORT_DATE_FORMAT
import com.azmiradi.kotlin_base.utilities.extensions.formatDate
import com.bumblebeeai.spire.home.jobs.data.models.dto.DriverJobItemDto
import com.bumblebeeai.spire.home.jobs.domain.model.DriverJob
import com.bumblebeeai.spire.home.jobs.domain.model.enums.JobStatus

internal object DriverJobMapper : Mapper<DriverJobItemDto, DriverJob>() {
    override fun dtoToDomain(model: DriverJobItemDto): DriverJob {
        return DriverJob(
            jobDetails = "Vehicle Rolling: ${if (model.rolling == 1) "Yes" else "No"}" +
                    " / Service Type: ${model.service?.type ?: "---"} / ETA:${
                        model.eta?.split("-")?.getOrNull(0)?.plus(":00") ?: DEFAULT_STRING_VALUE
                    } / Fault ${model.fault ?: DEFAULT_STRING_VALUE}",
            locationAddress = model.vehicleAddress ?: DEFAULT_STRING_VALUE,
            date = model.createdAt?.formatDate(ISO8601_FORMAT, SHORT_DATE_FORMAT)
                ?: DEFAULT_STRING_VALUE,
            id = model.id ?: 0,

            vehicleLocation = DriverJob.LocationDetails(
                address = model.vehicleAddress ?: DEFAULT_STRING_VALUE,
                lat = model.vehicleLat ?: 0.0,
                lung = model.vehicleLung ?: 0.0
            ),
            contact = model.customerPhone,
            destinationLocation = if (model.destLat == null && model.destLung == null) null else
                DriverJob.LocationDetails(
                    address = model.vehicleDest ?: DEFAULT_STRING_VALUE,
                    lat = model.destLat ?: 0.0,
                    lung = model.destLung ?: 0.0
                ),
            status = JobStatus.values().find { model.statusExternal == it.status }
                ?: throw BaseException.Unknown("Not found status"),
            jobNumber = model.ctJobNumber,
            vehicleDetails = model.theVehicle?.run {
                "Make:${make ?: DEFAULT_STRING_VALUE}/Model:${model.theVehicle.model ?: DEFAULT_STRING_VALUE}/Version:${versionType ?: DEFAULT_STRING_VALUE}/Color:${colour ?: DEFAULT_STRING_VALUE}/Transmission:${transmission ?: DEFAULT_STRING_VALUE}"
            } ?: DEFAULT_STRING_VALUE
        )
    }
}