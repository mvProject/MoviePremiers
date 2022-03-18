package com.mvproject.moviepremiers.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mvproject.moviepremiers.domain.model.Movie
import com.mvproject.moviepremiers.ui.theme.dimens

@Composable
fun MovieLogo(movieItem: Movie, modifier: Modifier = Modifier, onClick:() -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DateButton(text = movieItem.date){
            onClick.invoke()
        }
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8))
        Image(
            modifier = Modifier
                .size(90.dp, 130.dp)
                .clip(MaterialTheme.shapes.medium),
            painter = rememberAsyncImagePainter(movieItem.image),
            contentDescription = null,
            alignment = Alignment.CenterStart,
            contentScale = ContentScale.Crop
        )
    }
}