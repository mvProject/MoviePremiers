package com.mvproject.moviepremiers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvproject.moviepremiers.domain.model.Movie
import com.mvproject.moviepremiers.domain.repository.MovieRepository
import com.mvproject.moviepremiers.utils.getCurrentDay
import com.mvproject.moviepremiers.utils.getCurrentMonth
import com.mvproject.moviepremiers.utils.getCurrentYear
import com.mvproject.moviepremiers.utils.parseDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _loadState = MutableStateFlow(false)
    val loadState = _loadState.asStateFlow()

    private val _errorState = MutableSharedFlow<Int>()
    val errorState = _errorState.asSharedFlow()

    fun loadMovies() {
        viewModelScope.launch {
            _loadState.value = true
            withContext(Dispatchers.IO) {
                runCatching {
                    movieRepository.loadMovies(month, year)
                }.onSuccess { data->
                    val filteredResult =
                        data.filter { it.date.parseDate().toInt() >= day } as MutableList<Movie>
                    when (filteredResult.size) {
                        0 -> _movies.value = data
                        else -> _movies.value = filteredResult
                    }
                    _loadState.value = false
                }.onFailure {
                    _loadState.value = false
                    // _errorState.emit(handleException(it).getProperErrorMessage())
                }
            }
        }
    }
}