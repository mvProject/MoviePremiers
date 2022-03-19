package com.mvproject.moviepremiers.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.mvproject.moviepremiers.R
import com.mvproject.moviepremiers.data.model.Movie
import com.mvproject.moviepremiers.component.ErrorScreen
import com.mvproject.moviepremiers.component.LoadingScreen
import com.mvproject.moviepremiers.component.MovieCard
import com.mvproject.moviepremiers.ui.event.MoviesEvent
import com.mvproject.moviepremiers.ui.state.MovieState
import com.mvproject.moviepremiers.theme.MoviePremierTheme
import com.mvproject.moviepremiers.utils.parseDate
import com.mvproject.moviepremiers.utils.startCalendar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviePremierTheme {
                MainScreen(mainViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {
    LaunchedEffect(key1 = true) {
        mainViewModel.applyEvent(MoviesEvent.FetchMovies)
    }

    when (val state = mainViewModel.state.collectAsState().value) {
        is MovieState.Loading -> LoadingScreen()
        is MovieState.Error -> ErrorScreen(error = state.msg)
        is MovieState.MovieData -> MoviesList(movies = state.data)
    }
}

@Composable
fun MoviesList(movies: List<Movie>) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary)
    ) {
        items(movies) { movieItem ->
            val eventText =
                stringResource(id = R.string.add_premiere) + " - \"" + movieItem.titleRus + "\""
            MovieCard(movieItem = movieItem) {
                startCalendar(
                    context,
                    eventText,
                    movieItem.date.parseDate().toInt()
                )
            }
        }
    }
}
