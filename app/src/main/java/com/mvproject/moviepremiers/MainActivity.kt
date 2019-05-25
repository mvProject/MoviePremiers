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
import androidx.databinding.DataBindingUtil
import com.mvproject.moviepremiers.data.model.Movie
import com.mvproject.moviepremiers.data.viewmodel.MoviesViewModel
import com.mvproject.moviepremiers.databinding.ActivityMainBinding
import com.mvproject.moviepremiers.ui.MovieAdapter
import android.os.StrictMode



class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        binding.lifecycleOwner = this

        setSupportActionBar(toolbar)

        val upd = Updater(this)
        upd.checkUpdateFromUrl(BuildConfig.UPDATE_JSON_URL)

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.isError.observe(this, Observer<Throwable> {
            it?.let { Snackbar.make(movieList, it.message.toString(), Snackbar.LENGTH_LONG).show()}
        })

        viewModel.movies.observe(this, Observer<MutableList<Movie>> {
            it?.let {movieList.apply{
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MovieAdapter(it)
            } }
        })

        if(isNetworkConnected())
            viewModel.getMovieData()
        else
            toast(getString(R.string.no_internet_connection))
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }
}
