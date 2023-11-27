package com.bumblebeeai.spire.home.jobs.domain.model.enums


enum class JobStatus(val status: String, val title: String) {
    Assigned("Assigned", "Accept"),
    Accepted("Accepted", "On Route"),
    OnTheWayLocation("On the way (location)", "Arrived"),
    ArrivedLocation("Arrived (location)",""),
    CompletedLocation("Completed (location)",""),
    OnTheWayDestination("On the way (destination)","Arrived"),
    ArrivedDestination("Arrived (destination)",""),
    Completed("Completed","");

    fun getNextStatus(isSinglePoint: Boolean): JobStatus? {
        return when {
            isSinglePoint -> when (this) {
                Assigned -> Accepted
                Accepted -> OnTheWayLocation
                OnTheWayLocation -> ArrivedLocation
                ArrivedLocation -> CompletedLocation
                CompletedLocation -> null
                else -> null
            }

            else -> when (this) {
                Assigned -> Accepted
                Accepted -> OnTheWayLocation
                OnTheWayLocation -> ArrivedLocation
                ArrivedLocation -> CompletedLocation
                CompletedLocation -> OnTheWayDestination
                OnTheWayDestination -> ArrivedDestination
                ArrivedDestination -> CompletedLocation
                Completed -> null
            }
        }
    }

}