package com.mvproject.moviepremiers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
            Log.d("Date","doSomeOperation - " + Thread.currentThread().name)
            //Use dispatcher to switch between context
            val deferred = async(Dispatchers.IO) {
                //Working on background thread
                api.getMovies(month,year).await()
            }
            //Working on UI thread
            movies.value = deferred.await().filter{ it.date.parseDate().toInt() >= day} as MutableList<Movie>
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
