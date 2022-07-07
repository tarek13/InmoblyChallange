package com.inmobly.common_ui.utils.helper

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun convertDateFormatToAnother(
        srcDateFormat: String?,
        srcDate: String,
        destDateFormat: String?,
        locale: Locale?=null,
        useLocal: Boolean=false
    ): String {
        return try {
            val srcDf: DateFormat = SimpleDateFormat(srcDateFormat, Locale.ENGLISH)
            // parse the date string into Date object
            val date = srcDf.parse(srcDate)
            val destDf: DateFormat =
                SimpleDateFormat(destDateFormat, if (useLocal) locale else Locale.ENGLISH)
            // format the date into another format
            destDf.format(date)
        } catch (e: Exception) {
            srcDate
        }
    }
}