package br.com.estudos.biblioteca.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val NOME_AUTOR = "PREENCHER COM O NOME DO(A) ALUNO(A)"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreScreen(
    onVoltar: () -> Unit,
    onCompartilhar: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    TextButton(onClick = onVoltar) {
                        Text("Voltar", color = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                title = { Text("Sobre / Ajuda") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Biblioteca em Mãos",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Finalidade",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "Aplicativo acadêmico para organizar uma coleção pessoal de livros. " +
                    "É possível cadastrar obras, consultar seus dados e manter a lista atualizada durante o uso do aplicativo."
            )

            Text(
                text = "Como utilizar",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text("1. Na tela inicial, consulte os livros já cadastrados.")
            Text("2. Toque em ‘Cadastrar novo livro’ e preencha os campos do formulário.")
            Text("3. Salve o formulário para incluir o livro na lista ou toque em ‘Cancelar’ para sair sem cadastrar.")
            Text("4. Na lista, toque em ‘Ver detalhes’ para consultar todas as informações de uma obra.")
            Text("5. Use esta tela para conhecer o projeto ou compartilhar sua descrição.")

            Text(
                text = "Autor(a)",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(NOME_AUTOR)
            Text(
                text = "Projeto desenvolvido com Kotlin, Jetpack Compose, Material 3 e Navigation Compose.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = onCompartilhar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Compartilhar aplicativo")
            }
            OutlinedButton(
                onClick = onVoltar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Voltar")
            }
        }
    }
}
