package com.mvproject.moviepremiers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mvproject.updater.Updater
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel

    private val json = "https://raw.githubusercontent.com/mvProject/MoviePremiers/master/app/release/update.json"

    //   private var myJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val upd = Updater(this)
        upd.setUpdateJsonUrl(json)
        upd.start()

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        //viewModel.getData2()
        viewModel.isLoading.observe(this, Observer<Boolean> {
            it?.let { showLoadingDialog(it) }
        })

        viewModel.isError.observe(this, Observer<Throwable> {
            it?.let { Snackbar.make(movieList, it.message.toString(), Snackbar.LENGTH_LONG).show() }
        })

        viewModel.movies.observe(this, Observer<MutableList<Movie>> {
            it?.let {movieList.apply{
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MovieAdapter(it, this@MainActivity)
            } }
        })
        viewModel.getMovieData()
        //getData()
    }

//    private fun getData(){
//        myJob = CoroutineScope(Dispatchers.IO).launch {
//            viewModel.getMovieData()
//            withContext(Dispatchers.Main) {
//                loadingView.stop()
//                movieList.apply{
//                    layoutManager = LinearLayoutManager(this@MainActivity)
//                    adapter = MovieAdapter(viewModel.getData(), this@MainActivity)
//                }
//            }
//        }
//    }

    override fun onResume() {
        super.onResume()
        Log.d("Date","onResume")
    }

    private fun showLoadingDialog(show: Boolean) {
        if (show) loadingView.start() else loadingView.stop()
    }

}
