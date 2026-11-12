package br.com.estudos.biblioteca.data

import androidx.compose.runtime.mutableStateListOf
import br.com.estudos.biblioteca.model.Livro

/**
 * Estado em memória compartilhado pelas telas da aplicação.
 * A mutableStateListOf notifica o Compose quando um livro é adicionado.
 */
class BibliotecaStore {
    val livros = mutableStateListOf(
        Livro(
            id = "livro-inicial-001",
            titulo = "O Pequeno Príncipe",
            autor = "Antoine de Saint-Exupéry",
            anoPublicacao = 1943,
            genero = "Fábula",
            numeroPaginas = 96,
            sinopse = "Um pequeno príncipe viaja por planetas e conhece personagens que o ajudam a refletir sobre amizade, amor e responsabilidade."
        ),
        Livro(
            id = "livro-inicial-002",
            titulo = "Dom Casmurro",
            autor = "Machado de Assis",
            anoPublicacao = 1899,
            genero = "Romance",
            numeroPaginas = 256,
            sinopse = "Bentinho narra sua história com Capitu, revisitando lembranças e dúvidas que marcaram sua vida."
        ),
        Livro(
            id = "livro-inicial-003",
            titulo = "A Revolução dos Bichos",
            autor = "George Orwell",
            anoPublicacao = 1945,
            genero = "Sátira",
            numeroPaginas = 152,
            sinopse = "Os animais de uma fazenda se rebelam contra os humanos, mas a nova organização também passa a revelar relações de poder."
        )
    )

    fun adicionar(livro: Livro) {
        livros.add(0, livro)
    }

    fun buscarPorId(id: String): Livro? {
        return livros.firstOrNull { livro -> livro.id == id }
    }
}
