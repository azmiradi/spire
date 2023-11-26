package com.bumblebeeai.spire.home.jobs.data.models.dto

import com.google.gson.annotations.SerializedName

data class DriverJobDto(
	@field:SerializedName("data")
	val data: Data? = null
)

data class LinksItem(

	@field:SerializedName("active")
	val active: Boolean? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("url")
	val url: Any? = null
)

data class Data(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("data")
	val data: List<DriverJobItem?>? = null,

	@field:SerializedName("last_page")
	val lastPage: Int? = null,

	@field:SerializedName("next_page_url")
	val nextPageUrl: Any? = null,

	@field:SerializedName("prev_page_url")
	val prevPageUrl: Any? = null,

	@field:SerializedName("first_page_url")
	val firstPageUrl: String? = null,

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("last_page_url")
	val lastPageUrl: String? = null,

	@field:SerializedName("from")
	val from: Int? = null,

	@field:SerializedName("links")
	val links: List<LinksItem?>? = null,

	@field:SerializedName("to")
	val to: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
)

data class DriverJobItem(

	@field:SerializedName("ct_job_number")
	val ctJobNumber: String? = null,

	@field:SerializedName("is_damage_check")
	val isDamageCheck: Int? = null,

	@field:SerializedName("first_signature")
	val firstSignature: String? = null,

	@field:SerializedName("vehicle_class_id")
	val vehicleClassId: Int? = null,

	@field:SerializedName("member_no")
	val memberNo: Any? = null,

	@field:SerializedName("ans_auth_code")
	val ansAuthCode: Any? = null,

	@field:SerializedName("is_fnol")
	val isFnol: Int? = null,

	@field:SerializedName("vehicle_lat")
	val vehicleLat: String? = null,

	@field:SerializedName("replacement_base_id")
	val replacementBaseId: Any? = null,

	@field:SerializedName("eta")
	val eta: String? = null,

	@field:SerializedName("dest_lat")
	val destLat: String? = null,

	@field:SerializedName("ans_job_number")
	val ansJobNumber: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("vehicle_reg")
	val vehicleReg: String? = null,

	@field:SerializedName("assigned_time")
	val assignedTime: String? = null,

	@field:SerializedName("rep_lon")
	val repLon: Any? = null,

	@field:SerializedName("fnol_lon")
	val fnolLon: Any? = null,

	@field:SerializedName("vehicle_lon")
	val vehicleLon: String? = null,

	@field:SerializedName("odometer")
	val odometer: String? = null,

	@field:SerializedName("destination_base_id")
	val destinationBaseId: Any? = null,

	@field:SerializedName("customer_phone")
	val customerPhone: String? = null,

	@field:SerializedName("fnol_lat")
	val fnolLat: Any? = null,

	@field:SerializedName("rolling_reason")
	val rollingReason: String? = null,

	@field:SerializedName("vehicle_dest")
	val vehicleDest: String? = null,

	@field:SerializedName("assigned_driver")
	val assignedDriver: String? = null,

	@field:SerializedName("final_outcome")
	val finalOutcome: Any? = null,

	@field:SerializedName("fault_app")
	val faultApp: Any? = null,

	@field:SerializedName("send_gps_sms")
	val sendGpsSms: Int? = null,

	@field:SerializedName("vehicle_address")
	val vehicleAddress: String? = null,

	@field:SerializedName("customer_submitted")
	val customerSubmitted: Int? = null,

	@field:SerializedName("symptom")
	val symptom: String? = null,

	@field:SerializedName("base_lon")
	val baseLon: String? = null,

	@field:SerializedName("external_id_third")
	val externalIdThird: Any? = null,

	@field:SerializedName("account_holder")
	val accountHolder: Int? = null,

	@field:SerializedName("the_vehicle")
	val theVehicle: TheVehicle? = null,

	@field:SerializedName("second_signature")
	val secondSignature: Any? = null,

	@field:SerializedName("dest_lon")
	val destLon: String? = null,

	@field:SerializedName("is_rescheduled")
	val isRescheduled: Int? = null,

	@field:SerializedName("alternate_phone")
	val alternatePhone: Any? = null,

	@field:SerializedName("assigned_by")
	val assignedBy: String? = null,

	@field:SerializedName("address_base_id")
	val addressBaseId: Any? = null,

	@field:SerializedName("first_images")
	val firstImages: List<String?>? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("second_images")
	val secondImages: Any? = null,

	@field:SerializedName("note")
	val note: Any? = null,

	@field:SerializedName("due_time")
	val dueTime: String? = null,

	@field:SerializedName("the_driver")
	val theDriver: String? = null,

	@field:SerializedName("external_id_second")
	val externalIdSecond: String? = null,

	@field:SerializedName("replacement_location")
	val replacementLocation: Any? = null,

	@field:SerializedName("assigned_due_time")
	val assignedDueTime: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("external_id")
	val externalId: String? = null,

	@field:SerializedName("rep_lat")
	val repLat: Any? = null,

	@field:SerializedName("drivers_notes")
	val driversNotes: Any? = null,

	@field:SerializedName("fnol_base_id")
	val fnolBaseId: Any? = null,

	@field:SerializedName("caller_name")
	val callerName: Any? = null,

	@field:SerializedName("rolling")
	val rolling: Int? = null,

	@field:SerializedName("home_address")
	val homeAddress: Any? = null,

	@field:SerializedName("outcome_app")
	val outcomeApp: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("added_by")
	val addedBy: String? = null,

	@field:SerializedName("rescheduled_service_id")
	val rescheduledServiceId: Any? = null,

	@field:SerializedName("service_id")
	val serviceId: Int? = null,

	@field:SerializedName("base_lat")
	val baseLat: String? = null,

	@field:SerializedName("completed_by")
	val completedBy: Any? = null,

	@field:SerializedName("outcome")
	val outcome: String? = null,

	@field:SerializedName("to_pay")
	val toPay: String? = null,

	@field:SerializedName("status_external_id")
	val statusExternalId: String? = null,

	@field:SerializedName("client_company")
	val clientCompany: String? = null,

	@field:SerializedName("parking_hours")
	val parkingHours: Any? = null,

	@field:SerializedName("base_id")
	val baseId: String? = null,

	@field:SerializedName("fault")
	val fault: String? = null,

	@field:SerializedName("sub_account_holder")
	val subAccountHolder: Any? = null,

	@field:SerializedName("fnol_location")
	val fnolLocation: Any? = null,

	@field:SerializedName("status_external")
	val statusExternal: String? = null,

	@field:SerializedName("is_redelivery")
	val isRedelivery: Int? = null,

	@field:SerializedName("originator_note")
	val originatorNote: String? = null,

	@field:SerializedName("scheduled_datetime")
	val scheduledDatetime: Any? = null,

	@field:SerializedName("email_address")
	val emailAddress: Any? = null,

	@field:SerializedName("completed_time")
	val completedTime: Any? = null,

	@field:SerializedName("the_driver_external_id")
	val theDriverExternalId: String? = null,

	@field:SerializedName("service")
	val service: Service? = null,

	@field:SerializedName("ans_message_id")
	val ansMessageId: Any? = null,

	@field:SerializedName("progress")
	val progress: String? = null,

	@field:SerializedName("customer_name")
	val customerName: String? = null,

	@field:SerializedName("caller_number")
	val callerNumber: Any? = null,

	@field:SerializedName("override_reason")
	val overrideReason: String? = null
)

data class TheVehicle(

	@field:SerializedName("depot")
	val depot: String? = null,

	@field:SerializedName("version_type")
	val versionType: String? = null,

	@field:SerializedName("fleet_no")
	val fleetNo: Any? = null,

	@field:SerializedName("chassis")
	val chassis: Any? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("kw")
	val kw: String? = null,

	@field:SerializedName("odometer_type")
	val odometerType: String? = null,

	@field:SerializedName("colour")
	val colour: String? = null,

	@field:SerializedName("transmission")
	val transmission: String? = null,

	@field:SerializedName("engine_no")
	val engineNo: String? = null,

	@field:SerializedName("chassis_no")
	val chassisNo: String? = null,

	@field:SerializedName("body_no")
	val bodyNo: String? = null,

	@field:SerializedName("model")
	val model: String? = null,

	@field:SerializedName("engine_size")
	val engineSize: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("fuel_type")
	val fuelType: String? = null,

	@field:SerializedName("vehicle_reg")
	val vehicleReg: String? = null,

	@field:SerializedName("make")
	val make: String? = null
)

data class Service(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null
)
