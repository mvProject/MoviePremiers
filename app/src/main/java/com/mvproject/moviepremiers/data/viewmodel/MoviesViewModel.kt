package com.mvproject.moviepremiers.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvproject.moviepremiers.data.model.Movie
import com.mvproject.moviepremiers.network.ApiService
import com.mvproject.moviepremiers.utils.getCurrentDay
import com.mvproject.moviepremiers.utils.getCurrentMonth
import com.mvproject.moviepremiers.utils.getCurrentYear
import com.mvproject.moviepremiers.utils.parseDate
import kotlinx.coroutines.*

class MoviesViewModel : ViewModel() {

    private val api = ApiService().initApi()

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Throwable>()

    var movies = MutableLiveData<MutableList<Movie>>()

    private val day = getCurrentDay()
    private val month = getCurrentMonth()
    private val year = getCurrentYear()

    fun getMovieData() {
        uiScope.launch(handler) {
            //Working on UI thread
            isLoading.value = true
            //Use dispatcher to switch between context
            val deferred = async(Dispatchers.IO) {
                //Working on background thread
                api.getMoviesAsync(month,year).await()
            }
            //Working on UI thread
            val result = deferred.await()
            val filteredResult = result.filter{ it.date.parseDate().toInt() >= day} as MutableList<Movie>
            when (filteredResult.size) {
                0 -> movies.value = result
                else -> movies.value = filteredResult
            }
            isLoading.value = false
        }
    }
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private val handler = CoroutineExceptionHandler { _, throwable ->
        isLoading.value = false
        isError.value = throwable
    }


}
