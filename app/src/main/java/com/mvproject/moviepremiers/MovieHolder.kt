package com.mvproject.moviepremiers

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_item.view.*
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import java.util.*
import android.provider.CalendarContract
import com.squareup.picasso.Picasso

class MovieHolder(view: View?) : RecyclerView.ViewHolder(view!!){
    private val movieName = view?.findViewById(R.id.movie_name_rus) as TextView
    private val movieNameEng = view?.findViewById(R.id.movie_name_eng) as TextView
    private val movieDate = view?.findViewById(R.id.movie_date) as TextView
    private val movieGenre = view?.findViewById(R.id.movie_genre) as TextView
    private val movieActors = view?.findViewById(R.id.movie_actors) as TextView
    private val movieDirectors = view?.findViewById(R.id.movie_directors) as TextView
    private val movieCountries = view?.findViewById(R.id.movie_country) as TextView

    fun bindNote(movie: Movie,context: Context) {
        movieName.text = movie.titleRus
        movieNameEng.text = movie.titleEng
        movieDate.text = movie.date
        movieGenre.text = movie.genre
        movieActors.text = movie.actors
        movieCountries.text = movie.country
        movieDirectors.text = movie.directors

        Picasso.get()
            .load(movie.image)
            .into(itemView.movieLogo)

        movieDate.setOnClickListener {
            val month = Calendar.getInstance().get(Calendar.MONTH)
            val year = Calendar.getInstance().get(Calendar.YEAR)
            val eventText = "Премьера фильма - '${movie.titleRus}'"
            startCalendar(context,eventText,year,month,movie.date.parseDate().toInt())
        }
    }
}
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

fun startCalendar(context: Context,eventTitle : String,year : Int,month : Int, day : Int){
    val cal = Calendar.getInstance()
    cal.set(year,month,day)
    val builder = CalendarContract.CONTENT_URI.buildUpon().build()
    val intent = Intent(Intent.ACTION_INSERT,builder)
    intent.data = CalendarContract.Events.CONTENT_URI
    intent.putExtra(CalendarContract.Events.TITLE, eventTitle)
    intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.timeInMillis)

    startActivity(context,intent,null)
}