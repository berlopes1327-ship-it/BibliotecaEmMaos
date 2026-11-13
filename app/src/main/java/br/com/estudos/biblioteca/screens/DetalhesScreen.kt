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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.estudos.biblioteca.model.Livro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesScreen(
    livro: Livro?,
    onVoltar: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    TextButton(onClick = onVoltar) {
                        Text("Voltar", color = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                title = { Text("Detalhes do livro") },
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
            if (livro == null) {
                Text(
                    text = "Livro não encontrado",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error
                )
                Text("O identificador recebido não corresponde a nenhum livro da biblioteca.")
            } else {
                Text(
                    text = livro.titulo,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        InformacaoLivro(rotulo = "Autor", valor = livro.autor)
                        InformacaoLivro(rotulo = "Ano de publicação", valor = livro.anoPublicacao.toString())
                        InformacaoLivro(rotulo = "Gênero", valor = livro.genero)
                        InformacaoLivro(rotulo = "Número de páginas", valor = livro.numeroPaginas.toString())
                        InformacaoLivro(rotulo = "Identificador", valor = livro.id)
                        InformacaoLivro(rotulo = "Resumo / observações", valor = livro.sinopse)
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = onVoltar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Voltar à lista")
            }
        }
    }
}

@Composable
private fun InformacaoLivro(
    rotulo: String,
    valor: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            text = rotulo,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(text = valor, style = MaterialTheme.typography.bodyLarge)
    }
}
