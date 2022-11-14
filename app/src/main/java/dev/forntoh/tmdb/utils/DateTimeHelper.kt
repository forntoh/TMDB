package dev.forntoh.tmdb.utils

import org.threeten.bp.DateTimeUtils
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

fun Calendar?.getToDateString(format: String? = DateConstants.FORMAT_SIMPLE): String =
    if (this == null) "" else SimpleDateFormat(format, Locale.CANADA).format(time)

fun String.toCalendar(format: String? = DateConstants.FORMAT_SIMPLE): Calendar =
    DateTimeUtils.toGregorianCalendar(
        ZonedDateTime.of(
            LocalDateTime.parse(
                this.ifBlank { Calendar.getInstance().getToDateString(format) },
                DateTimeFormatter.ofPattern(format, Locale.getDefault())
            ), ZoneId.systemDefault()
        )
    )

object DateConstants {
    const val FORMAT_SIMPLE = "yyyy-MM-dd"
}