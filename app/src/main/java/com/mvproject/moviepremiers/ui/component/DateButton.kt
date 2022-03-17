package com.mvproject.moviepremiers.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mvproject.moviepremiers.R

@Composable
fun DateButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = colorResource(id = R.color.colorDateText),
    buttonBackgroundColor: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorResource(id = R.color.colorDateBack)
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
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
@Preview
fun DateButtonShow(){
    DateButton(text = "22.25.2021", onClick = {})
}