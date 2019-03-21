package com.mvproject.moviepremiers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Environment
import android.os.storage.StorageManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class ApiService() {

    fun initApi() : MovieApi {

    val cacheSize = (5 * 1024 * 1024).toLong()
    //val myCache = Cache(context.cacheDir, cacheSize)
    val myCache = Cache(Environment.getDownloadCacheDirectory(), cacheSize)
    val gson = GsonConverterFactory.create()

    val apiClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20,TimeUnit.SECONDS)
        .cache(myCache)
        .build()

    return Retrofit.Builder().apply {
        baseUrl(MovieApi.BASE_URL)
        addCallAdapterFactory(CoroutineCallAdapterFactory())
        addConverterFactory(gson)
        client(apiClient)
    }.build().create(MovieApi::class.java)
}

    private fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }


}