package com.alireza.brochure.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateMapper {

    @RequiresApi(Build.VERSION_CODES.O)
    private val defaultInputFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH)

    @RequiresApi(Build.VERSION_CODES.O)
    private val defaultOutputFormatter =
        DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm", Locale.ENGLISH)


    private const val RAW_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    private const val READABLE_PATTERN = "MMM dd, yyyy HH:mm"

    private val inputFormatter by lazy {
        SimpleDateFormat(RAW_PATTERN, Locale.ENGLISH).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    private val outputFormatter by lazy {
        SimpleDateFormat(READABLE_PATTERN, Locale.ENGLISH)
    }

    fun formatDate(rawDate: String): String {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                formatDateNewDevice(rawDate)
            else
                formatDateOldDevice(rawDate)
        } catch (e: Exception) {
            "Invalid date"
        }
    }


    private fun formatDateOldDevice(rawDate: String): String {
        return try {
            val date: Date = inputFormatter.parse(rawDate)!!
            outputFormatter.format(date)
        } catch (e: Exception) {
            "Invalid date"
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDateNewDevice(
        rawDate: String,
        inputFormat: DateTimeFormatter = defaultInputFormatter,
        outputFormat: DateTimeFormatter = defaultOutputFormatter
    ): String {
        return try {
            val dateTime = OffsetDateTime.parse(rawDate, inputFormat)
            outputFormat.format(dateTime)
        } catch (e: Exception) {
            "Invalid date"
        }
    }
}