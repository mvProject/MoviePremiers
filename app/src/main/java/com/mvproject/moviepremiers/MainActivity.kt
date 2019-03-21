package com.mvproject.moviepremiers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.loadingview.LoadingDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel
    private var myJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        getData()
    }

    private fun getData(){
        loadingView.start()
        myJob = CoroutineScope(Dispatchers.IO).launch {
            viewModel.getMovieData()
            withContext(Dispatchers.Main) {
                loadingView.stop()
                movieList.apply{
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = MovieAdapter(viewModel.getData(), this@MainActivity)
                }
            }
        }
    }
}
