package com.mvproject.moviepremiers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.moviepremiers.data.model.Movie
import com.mvproject.moviepremiers.data.repository.MovieRepository
import com.mvproject.moviepremiers.ui.event.MoviesEvent
import com.mvproject.moviepremiers.ui.state.MovieState
import com.mvproject.moviepremiers.utils.getCurrentDay
import com.mvproject.moviepremiers.utils.getCurrentMonth
import com.mvproject.moviepremiers.utils.getCurrentYear
import com.mvproject.moviepremiers.utils.parseDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) :
    ViewModel() {

    private val day = getCurrentDay()
    private val month = getCurrentMonth()
    private val year = getCurrentYear()

    private val _state: MutableStateFlow<MovieState> =
        MutableStateFlow(MovieState.Loading)

    val state: MutableStateFlow<MovieState> = _state

    val _event: Channel<MoviesEvent> = Channel()

    init {
        observeIntents()
    }

    private fun processIntents(event: MoviesEvent) {
        when (event) {
            MoviesEvent.FetchMovies -> loadMovies()
        }
    }

    private fun observeIntents() {
        viewModelScope.launch {
            _event.consumeAsFlow().collect { processIntents(it) }
        }
    }

    suspend fun applyEvent(event: MoviesEvent){
        _event.send(event)
    }

    private fun loadMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                runCatching {
                    _state.value = MovieState.Loading
                    movieRepository.loadMovies(month, year)
                }.onSuccess { data ->
                    val filteredResult =
                        data.filter { it.date.parseDate().toInt() >= day } as MutableList<Movie>
                    val result = when (filteredResult.size) {
                        0 -> data
                        else -> filteredResult
                    }
                    _state.value = MovieState.MovieData(data = result)

                }.onFailure {
                    _state.value = MovieState.Error(msg = it.localizedMessage?: "some error")
                }
            }
        }
    }
}