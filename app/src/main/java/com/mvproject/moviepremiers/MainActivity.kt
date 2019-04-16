package com.mvproject.moviepremiers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mvproject.updater.Updater
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import android.net.ConnectivityManager
import com.mvproject.moviepremiers.data.model.Movie
import com.mvproject.moviepremiers.data.viewmodel.MoviesViewModel
import com.mvproject.moviepremiers.ui.MovieAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel
    private val updateJson = "https://raw.githubusercontent.com/mvProject/MoviePremiers/master/app/release/update.json"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val upd = Updater(this)
        upd.checkUpdateFromUrl(updateJson)

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        viewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let { showLoadingDialog(it) }
        })

        viewModel.isError.observe(this, Observer<Throwable> {
            it?.let { Snackbar.make(movieList, it.message.toString(), Snackbar.LENGTH_LONG).show()}
        })

        viewModel.movies.observe(this, Observer<MutableList<Movie>> {
            it?.let {movieList.apply{
                if (it.size>0){
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = MovieAdapter(it, this@MainActivity)
                }
                else toast(context.getString(R.string.no_premiers_anymore))
            } }
        })

        if(isNetworkConnected())
            viewModel.getMovieData()
        else
            toast(getString(R.string.no_internet_connection))
    }

    private fun showLoadingDialog(show: Boolean) {
        if (show) loadingView.start() else loadingView.stop()
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }
}
