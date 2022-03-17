package com.mvproject.moviepremiers.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mvproject.moviepremiers.R

@Composable
fun DateTag(date: String, modifier: Modifier = Modifier, onDateClick:()->Unit) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.colorDateBorder),
                shape = RoundedCornerShape(80.dp)
            )
            .background(color = colorResource(id = R.color.colorDateText))
            .padding(10.dp)
            .clickable { onDateClick() }

    ) {
        Text(
            text = date,
            color = colorResource(id = R.color.colorDateBack),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
        )
    }
}

@Preview
@Composable
fun DateTagPreview() {
    DateTag(date = "22.03.2021", onDateClick = {})
}