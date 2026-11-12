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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import br.com.estudos.biblioteca.model.Livro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(
    onSalvar: (Livro) -> Unit,
    onCancelar: () -> Unit
) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var autor by rememberSaveable { mutableStateOf("") }
    var ano by rememberSaveable { mutableStateOf("") }
    var genero by rememberSaveable { mutableStateOf("") }
    var paginas by rememberSaveable { mutableStateOf("") }
    var sinopse by rememberSaveable { mutableStateOf("") }
    var mostrarErros by rememberSaveable { mutableStateOf(false) }

    val anoConvertido = ano.toIntOrNull()
    val paginasConvertidas = paginas.toIntOrNull()

    val erroTitulo = if (mostrarErros && titulo.isBlank()) "Informe o título do livro." else null
    val erroAutor = if (mostrarErros && autor.isBlank()) "Informe o nome do autor." else null
    val erroAno = if (mostrarErros && (anoConvertido == null || anoConvertido !in 1000..2100)) {
        "Digite um ano entre 1000 e 2100."
    } else null
    val erroPaginas = if (mostrarErros && (paginasConvertidas == null || paginasConvertidas <= 0)) {
        "Informe um número de páginas maior que zero."
    } else null

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastrar livro") },
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
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Preencha os dados do livro",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Título *") },
                singleLine = true,
                isError = erroTitulo != null,
                supportingText = { erroTitulo?.let { Text(it) } }
            )
            OutlinedTextField(
                value = autor,
                onValueChange = { autor = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Autor *") },
                singleLine = true,
                isError = erroAutor != null,
                supportingText = { erroAutor?.let { Text(it) } }
            )
            OutlinedTextField(
                value = ano,
                onValueChange = { ano = it.filter(Char::isDigit).take(4) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Ano de publicação *") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = erroAno != null,
                supportingText = { erroAno?.let { Text(it) } }
            )
            OutlinedTextField(
                value = genero,
                onValueChange = { genero = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Gênero literário") },
                singleLine = true
            )
            OutlinedTextField(
                value = paginas,
                onValueChange = { paginas = it.filter(Char::isDigit).take(5) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Número de páginas *") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = erroPaginas != null,
                supportingText = { erroPaginas?.let { Text(it) } }
            )
            OutlinedTextField(
                value = sinopse,
                onValueChange = { sinopse = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Resumo / observações") },
                minLines = 3
            )

            Text(
                text = "* Campos obrigatórios. Ano e número de páginas precisam ser válidos.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(2.dp))
            Button(
                onClick = {
                    mostrarErros = true
                    if (
                        titulo.isNotBlank() &&
                        autor.isNotBlank() &&
                        anoConvertido != null && anoConvertido in 1000..2100 &&
                        paginasConvertidas != null && paginasConvertidas > 0
                    ) {
                        onSalvar(
                            Livro(
                                titulo = titulo.trim(),
                                autor = autor.trim(),
                                anoPublicacao = anoConvertido,
                                genero = genero.trim().ifBlank { "Não informado" },
                                numeroPaginas = paginasConvertidas,
                                sinopse = sinopse.trim().ifBlank { "Nenhum resumo informado." }
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar livro")
            }
            OutlinedButton(
                onClick = onCancelar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}
