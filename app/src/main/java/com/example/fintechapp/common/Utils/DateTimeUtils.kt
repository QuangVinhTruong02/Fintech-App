package com.example.fintechapp.common.Utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeUtils {

    const val YYYY_MM_DD_DATE_TIME : String = "yyyy-MM-dd"
    const val HH_MM_SS_DATE_TIME : String = "HH:mm:ss"

    fun getCurrentDateTime(): String {
        val currentDate = Date()
        val formatter = SimpleDateFormat("$YYYY_MM_DD_DATE_TIME $HH_MM_SS_DATE_TIME", Locale.getDefault())
        return formatter.format(currentDate)
    }

    fun getFormattedDate(pattern: String = YYYY_MM_DD_DATE_TIME): String {
        val currentDate = Date()
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(currentDate)
    }

    fun parseDateFromString(dateString: String, pattern: String = "$YYYY_MM_DD_DATE_TIME $HH_MM_SS_DATE_TIME"): Date? {
        return try {
            val formatter = SimpleDateFormat(pattern, Locale.getDefault())
            formatter.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }
}