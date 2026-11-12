package br.com.estudos.biblioteca.model

import java.util.UUID

data class Livro(
    val id: String = UUID.randomUUID().toString(),
    val titulo: String,
    val autor: String,
    val anoPublicacao: Int,
    val genero: String,
    val numeroPaginas: Int,
    val sinopse: String
)
