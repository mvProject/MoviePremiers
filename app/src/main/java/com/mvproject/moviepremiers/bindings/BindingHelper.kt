package com.mvproject.moviepremiers.bindings

import android.view.View
import com.mvproject.moviepremiers.R
import com.mvproject.moviepremiers.data.model.Movie
import com.mvproject.moviepremiers.utils.parseDate
import com.mvproject.moviepremiers.utils.startCalendar
import java.util.*

class BindingHelper{
    
    fun addToCalendar(view : View, movie : Movie) {
        val month = Calendar.getInstance().get(Calendar.MONTH)
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val eventText = view.context.getString(R.string.add_premiere) + " - \"" + movie.titleRus + "\""
        startCalendar(
            view.context,
            eventText,
            year,
            month,
            movie.date.parseDate().toInt()
        )
    }
}
