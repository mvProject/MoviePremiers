package com.mvproject.moviepremiers.ui.event

sealed class MoviesEvent {
    object FetchMovies : MoviesEvent()
}