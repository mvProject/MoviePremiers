package com.mvproject.moviepremiers.ui.state

import com.mvproject.moviepremiers.data.model.Movie

sealed class MovieState {
    object Loading : MovieState()
    data class Error(val msg: String) : MovieState()
    data class MovieData(val data: List<Movie>) : MovieState()
}