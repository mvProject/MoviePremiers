package com.mvproject.moviepremiers.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mvproject.moviepremiers.R
import com.mvproject.moviepremiers.domain.model.Movie

@Composable
fun MovieCard(movieItem: Movie, onClickListener:() -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        backgroundColor = Color.LightGray,
        border = BorderStroke(1.dp, colorResource(id = R.color.colorDateBorder)),
        elevation = 12.dp
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            MovieLogo(
                movieItem = movieItem,
                Modifier
                    .align(Alignment.CenterVertically),
                onClick = onClickListener
            )
            Spacer(modifier = Modifier.width(8.dp))
            MovieInfo(movieItem = movieItem)
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    MovieCard(testMovie, onClickListener = {})
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
