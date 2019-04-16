package com.mvproject.moviepremiers.utils

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import androidx.core.content.ContextCompat
import java.util.*

fun startCalendar(context: Context, eventTitle : String, year : Int, month : Int, day : Int){
    val cal = Calendar.getInstance()
    cal.set(year,month,day)
    val builder = CalendarContract.CONTENT_URI.buildUpon().build()
    val intent = Intent(Intent.ACTION_INSERT,builder)
    intent.data = CalendarContract.Events.CONTENT_URI
    intent.putExtra(CalendarContract.Events.TITLE, eventTitle)
    intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.timeInMillis)

    ContextCompat.startActivity(context, intent, null)
}