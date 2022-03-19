package com.mvproject.moviepremiers.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mvproject.moviepremiers.theme.MoviePremierTheme
import com.mvproject.moviepremiers.theme.dimens

@Composable
fun DateButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = MaterialTheme.colors.onSurface,
    buttonBackgroundColor: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.secondary
    ),
    fontSize: Int = 14,
    onClick: () -> Unit,
) {
    Button(
        shape = RoundedCornerShape(60),
        modifier = modifier,
        colors = buttonBackgroundColor,
        onClick = { onClick.invoke() },
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = fontSize.sp,
            modifier = Modifier.padding(MaterialTheme.dimens.size2)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DateButtonShowPreview() {
    MoviePremierTheme {
        DateButton(text = "22.25.2021", onClick = {})
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DateButtonShowPreviewDark() {
    MoviePremierTheme {
        DateButton(text = "22.25.2021", onClick = {})
    }
}