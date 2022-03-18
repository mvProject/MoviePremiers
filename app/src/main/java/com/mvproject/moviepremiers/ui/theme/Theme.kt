package com.mvproject.moviepremiers.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val DarkColorPalette = darkColors(
    primary = lightSteelBlue,
    secondary = lightSteelBlueAlpha60,
    background = lightSteelBlue,
    surface = darkSlateGray,
    onPrimary = midnightBlue,
    onBackground = midnightBlue,
    onSurface = lightSlateGray,
)

private val LightColorPalette = lightColors(
    primary = lightBlack,
    secondary = lightBlackAlpha60,
    background = lightBlack,
    surface = darkSlateGray,
    onPrimary = chocolate,
    onBackground = chocolate,
    onSurface = lightSlateGray
)

@Composable
fun MoviePremierTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    CompositionLocalProvider(
        LocalDimens provides Dimens(),
        LocalTypography provides AppTypography()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}