package com.mvproject.moviepremiers.network

import android.os.Environment
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    fun initApi() : MovieApi {

        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(Environment.getDownloadCacheDirectory(), cacheSize)
        val gson = GsonConverterFactory.create()

        val apiClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .cache(myCache)
            .build()

        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            addConverterFactory(gson)
            client(apiClient)
        }.build().create(MovieApi::class.java)
    }
    companion object {
        const val BASE_URL = "http://mvpapi.herokuapp.com/"
    }
}