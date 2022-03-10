package com.mvproject.moviepremiers.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvproject.moviepremiers.domain.model.Movie
import com.mvproject.moviepremiers.domain.adapter.MoviesAdapter
import com.mvproject.moviepremiers.R
import com.mvproject.moviepremiers.databinding.ActivityMainBinding
import com.mvproject.moviepremiers.utils.OnClickListener
import com.mvproject.moviepremiers.utils.collectFlow
import com.mvproject.moviepremiers.utils.parseDate
import com.mvproject.moviepremiers.utils.startCalendar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val itemClick = OnClickListener<Movie> { movie ->
                val month = Calendar.getInstance().get(Calendar.MONTH)
                val year = Calendar.getInstance().get(Calendar.YEAR)
                val eventText =
                    getString(R.string.add_premiere) + " - \"" + movie.titleRus + "\""
                startCalendar(
                    this@MainActivity,
                    eventText,
                    year,
                    month,
                    movie.date.parseDate().toInt()
                )
            }

            moviesAdapter = MoviesAdapter(itemClick)

            movieList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = moviesAdapter
            }
        }

        collectFlow(mainViewModel.errorState) { error ->
            // showErrorMessage(getString(error))
        }

        collectFlow(mainViewModel.loadState) {
            // progressBar.isVisible = it
        }

        collectFlow(mainViewModel.movies) { movies ->
            moviesAdapter.items = movies
        }

        mainViewModel.loadMovies()
    }
}
