package com.mvproject.moviepremiers.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mvproject.moviepremiers.theme.appTypography
import com.mvproject.moviepremiers.theme.dimens

@Composable
fun NoItemsScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(96.dp),
                    imageVector = Icons.Filled.Info,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "No Items Found"
                )

                Text(
                    modifier = Modifier
                        .padding(
                            top = MaterialTheme.dimens.size16,
                            bottom = MaterialTheme.dimens.size24
                        ),
                    text = "No Items Found",
                    style = MaterialTheme.appTypography.textSemiBold,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
    }
}

@Composable
fun ErrorScreen(error: String) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Oops, $error!")
    }
}