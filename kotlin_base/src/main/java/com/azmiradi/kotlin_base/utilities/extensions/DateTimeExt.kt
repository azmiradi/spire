package com.azmiradi.kotlin_base.utilities.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Date.getCurrentDateTimeUTC(pattern: String): String {
    val dateFormatGmt = SimpleDateFormat(pattern, Locale.US)
    dateFormatGmt.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormatGmt.format(this)
}


fun String.convertStringToDate(pattern: String): Date? {
    val format =
        SimpleDateFormat(pattern, Locale.US)
    format.timeZone = TimeZone.getTimeZone("UTC")

    return format.parse(this)
}


fun String.formatDate(oldDatePattern: String, newDatePattern: String): String {
    try {
        val inputFormat = SimpleDateFormat(oldDatePattern, Locale.US)
        val outputFormat = SimpleDateFormat(newDatePattern, Locale.US)
        val date = inputFormat.parse(this)

        date?.let {
            return outputFormat.format(date)
        } ?: run {
            return "---"
        }
    } catch (e: ParseException) {
        return "---"
    }
}