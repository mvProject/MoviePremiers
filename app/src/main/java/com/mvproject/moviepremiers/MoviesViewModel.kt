package com.mvproject.moviepremiers

import android.util.Log
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import java.util.*


class MoviesViewModel : ViewModel() {

    private var movies = mutableListOf<Movie>()
    private val api = ApiService().initApi()


    private val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    private val month = DecimalFormat("00").format(Calendar.getInstance().get(Calendar.MONTH)+1)
    private val year = Calendar.getInstance().get(Calendar.YEAR).toString()

    suspend fun getMovieData(){
        movies = api.getMovies(month,year).await()
    }
    fun getData() : MutableList<Movie>{

        Log.d("Date",day.toString())
        return movies.filter{ it.date.parseDate().toInt() > day} as MutableList<Movie>
        //return movies
    }
}
