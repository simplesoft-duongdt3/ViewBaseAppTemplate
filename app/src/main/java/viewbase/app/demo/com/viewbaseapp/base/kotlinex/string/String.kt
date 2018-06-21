package viewbase.app.demo.com.viewbaseapp.base.kotlinex.string

import viewbase.app.demo.com.viewbaseapp.base.util.AccentRemover
import java.text.SimpleDateFormat
import java.util.*

fun String.calDiffTimeMiliToNow(format: String): Long {
    return try {
        val timeMilliSecondDateCurrent: Long = Calendar.getInstance(Locale.US).timeInMillis
        val timeMilliSecondDate = this.toDate(format).time
        timeMilliSecondDateCurrent - timeMilliSecondDate
    } catch (e: Exception) {
        0
    }
}

fun String.calDiffTimeMiliToDate(date: String, format: String): Long {
    try {
        Date()
        val timeMilliSecondDate = this.toDate(format).time
        val timeMilliSecondDateToDate: Long = date.toTimeMilliSecond(format)
        return timeMilliSecondDateToDate - timeMilliSecondDate
    } catch (e: Exception) {
    }
    return 0
}

fun String.toTimeMilliSecond(format: String): Long = this.toDate(format).time

fun String.removeAccentAndSpace(): String = AccentRemover.removeAccentAndSpace(this)

fun String?.getValueOrDefaultIsEmpty(): String {
    return this ?: ""
}

fun String.toDate(format: String): Date = SimpleDateFormat(format, Locale.US).parse(this)