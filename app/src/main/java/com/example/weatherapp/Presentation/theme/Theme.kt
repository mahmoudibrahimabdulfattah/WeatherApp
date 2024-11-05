package com.example.weatherapp.Presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme

private val LightColorScheme = lightColorScheme(
    primary = PurpleLight,
    secondary = PurpleGreyLight,
    tertiary = PinkLight,
    background = LightBackground,
    surface = LightSurface,
    onPrimary = LightTextPrimary,
    onSecondary = LightTextSecondary,
)

private val DarkColorScheme = darkColorScheme(
    primary = PurpleDark,
    secondary = PurpleGreyDark,
    tertiary = PinkDark,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = DarkTextPrimary,
    onSecondary = DarkTextSecondary,
)

@Composable
fun WeatherAppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}