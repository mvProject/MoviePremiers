package com.mvproject.moviepremiers.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mvproject.moviepremiers.domain.model.Movie

@Composable
fun MovieInfo(movieItem: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = movieItem.titleEng,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movieItem.titleRus,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movieItem.country,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movieItem.genre,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movieItem.directors,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movieItem.actors,
            style = MaterialTheme.typography.caption
        )
    }
}