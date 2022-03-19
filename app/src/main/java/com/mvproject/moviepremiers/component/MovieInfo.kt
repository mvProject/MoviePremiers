package com.mvproject.moviepremiers.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.mvproject.moviepremiers.data.model.Movie
import com.mvproject.moviepremiers.theme.appTypography
import com.mvproject.moviepremiers.theme.dimens

@Composable
fun MovieInfo(movieItem: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(MaterialTheme.dimens.size8)
    ) {
        Text(
            text = movieItem.titleEng,
            style = MaterialTheme.appTypography.textSemiBold
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size2))
        Text(
            text = movieItem.titleRus,
            style = MaterialTheme.appTypography.textBold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size2))
        Text(
            text = movieItem.country,
            style = MaterialTheme.appTypography.textMedium,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size2))
        Text(
            text = movieItem.genre,
            style = MaterialTheme.appTypography.textNormal
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size2))
        Text(
            text = movieItem.directors,
            style = MaterialTheme.appTypography.textNormal,
            fontSize = 10.sp
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.size2))
        Text(
            text = movieItem.actors,
            style = MaterialTheme.appTypography.textNormal,
            fontSize = 10.sp
        )
    }
}