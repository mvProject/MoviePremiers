package com.mvproject.moviepremiers.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mvproject.moviepremiers.domain.model.Movie
import com.mvproject.moviepremiers.ui.theme.MoviePremierTheme
import com.mvproject.moviepremiers.ui.theme.dimens

@Composable
fun MovieCard(movieItem: Movie, onClickListener: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(MaterialTheme.dimens.size8),
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke(
            MaterialTheme.dimens.size2,
            MaterialTheme.colors.primary
        ),
        elevation = MaterialTheme.dimens.size8
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.dimens.size16,
                    vertical = MaterialTheme.dimens.size8
                )
        ) {
            MovieLogo(
                movieItem = movieItem,
                Modifier
                    .align(Alignment.CenterVertically),
                onClick = onClickListener
            )
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.size8))
            MovieInfo(movieItem = movieItem)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCardPreview() {
    MoviePremierTheme {
        MovieCard(testMovie, onClickListener = {})
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MovieCardPreviewDark() {
    MoviePremierTheme {
        MovieCard(testMovie, onClickListener = {})
    }
}

val testMovie = Movie(
    date = "31 марта",
    titleRus = "Ёж Соник 2",
    titleEng = "Sonic the Hedgehog 2",
    genre = "боевик комедия приключения фантастика",
    country = "США, Канада",
    directors = "Режиссер: Джефф Фаулер",
    actors = "Актеры: Бен Шварц Идрис Эльба Джим Керри",
    link = "https://www.kinofilms.ua/movie/899058/",
    image = "https://www.kinofilms.ua/images/movies/big/899058_ua.jpg"
)
