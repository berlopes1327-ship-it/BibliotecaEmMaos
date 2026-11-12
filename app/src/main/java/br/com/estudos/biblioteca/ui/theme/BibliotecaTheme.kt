package br.com.estudos.biblioteca.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CoresBiblioteca = lightColorScheme(
    primary = Color(0xFF315C4B),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFB56B40),
    onSecondary = Color(0xFFFFFFFF),
    background = Color(0xFFF5F2EA),
    onBackground = Color(0xFF252A25),
    surface = Color(0xFFFFFEFC),
    onSurface = Color(0xFF252A25),
    error = Color(0xFFBA1A1A)
)

@Composable
fun BibliotecaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CoresBiblioteca,
        content = content
    )
}
