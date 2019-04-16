package com.mvproject.moviepremiers.network

import com.mvproject.moviepremiers.data.model.Movie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/{month}/{year}")
    fun getMoviesAsync(
        @Path("month") month : String,
        @Path("year") year : String
    ) : Deferred<MutableList<Movie>>


}