package com.mvproject.moviepremiers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.loadingview.LoadingDialog
import jp.misyobun.lib.versionupdater.MSBVersionUpdater
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

    override fun onResume() {
        super.onResume()
        Log.d("Date","onResume")
        val updater = MSBVersionUpdater(this)
        updater.endpoint = "https://github.com/mvProject/MoviePremiers/blob/master/app/release/update.json"
        updater.title = "MoviePremiers Update" // Notice
        updater.message = "You can update to version! 1.2.0" // Yout can update new version!
        updater.executeVersionCheck()
    }
}
