package br.com.estudos.biblioteca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.estudos.biblioteca.navigation.AppNavigation
import br.com.estudos.biblioteca.ui.theme.BibliotecaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BibliotecaTheme {
                AppNavigation()
            }
        }
    }
}
