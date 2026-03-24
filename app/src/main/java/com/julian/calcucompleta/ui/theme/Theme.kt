package com.julian.calcucompleta.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Colores personalizados
private val BlackBackground = Color(0xFF000000)
private val DarkButton = Color(0xFF1C1C1C)
private val GrayButton = Color(0xFF505050)
private val OrangeButton = Color(0xFFFF9800)

// Tema oscuro
private val DarkColorScheme = darkColorScheme(
    background = BlackBackground,
    surface = BlackBackground,

    primary = OrangeButton,     // Operadores
    secondary = DarkButton,     // Números
    tertiary = GrayButton,      // Botones especiales

    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White
)

@Composable
fun CalcuCompletaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme, // Forzar modo oscuro
        typography = Typography,
        content = content
    )
}