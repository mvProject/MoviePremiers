package com.mvproject.moviepremiers.network

import com.mvproject.moviepremiers.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://mvpapi.herokuapp.com/"

interface MovieApi {
    @GET("movie/{month}/{year}")
    suspend fun getMovies(
        @Path("month") month : String,
        @Path("year") year : String
    ) : List<Movie>


}