package br.com.estudos.biblioteca.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.estudos.biblioteca.model.Livro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    livros: List<Livro>,
    onAbrirDetalhes: (String) -> Unit,
    onAbrirCadastro: () -> Unit,
    onAbrirSobre: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Biblioteca em Mãos") },
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
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Minha coleção",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "${livros.size} livro(s) cadastrado(s)",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onAbrirCadastro,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cadastrar novo livro")
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = onAbrirSobre,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sobre / Ajuda")
            }
            Spacer(modifier = Modifier.height(14.dp))

            if (livros.isEmpty()) {
                Text("Sua biblioteca está vazia. Cadastre o primeiro livro.")
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items = livros, key = { it.id }) { livro ->
                        LivroCard(
                            livro = livro,
                            onAbrirDetalhes = { onAbrirDetalhes(livro.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LivroCard(
    livro: Livro,
    onAbrirDetalhes: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = livro.titulo,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Autor: ${livro.autor}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "${livro.anoPublicacao} • ${livro.genero} • ${livro.numeroPaginas} páginas",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            TextButton(
                onClick = onAbrirDetalhes,
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 4.dp)
            ) {
                Text("Ver detalhes")
            }
        }
    }
}
