package com.mvproject.moviepremiers.utils

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import androidx.core.content.ContextCompat
import java.text.DecimalFormat
import java.util.*

fun startCalendar(context: Context, eventTitle: String, day: Int) {
    val cal = Calendar.getInstance()
    val month = cal.get(Calendar.MONTH)
    val year = cal.get(Calendar.YEAR)
    cal.set(year, month, day)

    val builder = CalendarContract
        .CONTENT_URI
        .buildUpon()
        .build()

    val intent = Intent(Intent.ACTION_INSERT, builder).apply {
        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.Events.TITLE, eventTitle)
        putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.timeInMillis)
    }

    ContextCompat.startActivity(context, intent, null)
}

fun getCurrentYear() = Calendar.getInstance().get(Calendar.YEAR).toString()

fun getCurrentMonth() = DecimalFormat("00").format(Calendar.getInstance().get(Calendar.MONTH)+1)!!

fun getCurrentDay() = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

fun String.parseDate() : String {
    var str = this
    try {
        val date = str.split(" ")
        str = date[0]
    }
    finally {
        return str
    }
}