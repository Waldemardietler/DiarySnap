package com.example.diarysnap.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = Color.White,

    secondary = SecondaryBlue,
    onSecondary = Color.Black,

    background = LightBlueBg,
    onBackground = Color(0xFF0F172A),

    surface = Color.White,
    onSurface = Color(0xFF0F172A)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = Color.Black,

    secondary = Color(0xFF80DEEA),
    onSecondary = Color.Black,

    background = Color(0xFF0B1220),
    onBackground = Color(0xFFE5E7EB),

    surface = Color(0xFF111827),
    onSurface = Color(0xFFE5E7EB)
)

@Composable
fun DiarySnapTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}