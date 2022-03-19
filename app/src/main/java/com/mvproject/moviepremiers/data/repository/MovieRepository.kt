package com.mvproject.moviepremiers.data.repository

import com.mvproject.moviepremiers.network.MovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi
) {
    suspend fun loadMovies(month: String, year: String) = movieApi.getMovies(month, year)
}