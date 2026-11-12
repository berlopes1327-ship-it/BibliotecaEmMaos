package br.com.estudos.biblioteca.navigation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.estudos.biblioteca.data.BibliotecaStore
import br.com.estudos.biblioteca.screens.CadastroScreen
import br.com.estudos.biblioteca.screens.DetalhesScreen
import br.com.estudos.biblioteca.screens.HomeScreen
import br.com.estudos.biblioteca.screens.SobreScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // O mesmo armazenamento é criado acima do NavHost e compartilhado pelos destinos.
    val biblioteca = remember { BibliotecaStore() }
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                livros = biblioteca.livros,
                onAbrirDetalhes = { id -> navController.navigate(Routes.detalhes(id)) },
                onAbrirCadastro = {
                    navController.navigate(Routes.CADASTRO) {
                        launchSingleTop = true
                    }
                },
                onAbrirSobre = {
                    navController.navigate(Routes.SOBRE) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.CADASTRO) {
            CadastroScreen(
                onSalvar = { novoLivro ->
                    biblioteca.adicionar(novoLivro)
                    navController.popBackStack()
                },
                onCancelar = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.DETALHES,
            arguments = listOf(
                navArgument(Routes.ARG_LIVRO_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val idRecebido = backStackEntry.arguments?.getString(Routes.ARG_LIVRO_ID).orEmpty()
            DetalhesScreen(
                livro = biblioteca.buscarPorId(idRecebido),
                onVoltar = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.SOBRE) {
            SobreScreen(
                onVoltar = {
                    navController.popBackStack()
                },
                onCompartilhar = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "Conheça o Biblioteca em Mãos, um aplicativo Android de gerenciamento de livros desenvolvido em Kotlin e Jetpack Compose para uma atividade acadêmica."
                        )
                    }

                    try {
                        context.startActivity(
                            Intent.createChooser(intent, "Compartilhar Biblioteca em Mãos")
                        )
                    } catch (_: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "Nenhum aplicativo disponível para compartilhar.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            )
        }
    }
}
