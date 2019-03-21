package com.mvproject.moviepremiers

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/{month}/{year}")
    fun getMovies(
        @Path("month") month : String,
        @Path("year") year : String
    ) : Deferred<MutableList<Movie>>

    companion object {
        val BASE_URL = "http://mvpapi.herokuapp.com/"
    }
}