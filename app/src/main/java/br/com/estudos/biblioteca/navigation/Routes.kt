package br.com.estudos.biblioteca.navigation

import android.net.Uri

object Routes {
    const val HOME = "home"
    const val CADASTRO = "cadastro"
    const val SOBRE = "sobre"
    const val ARG_LIVRO_ID = "livroId"
    const val DETALHES = "detalhes/{$ARG_LIVRO_ID}"

    fun detalhes(livroId: String): String = "detalhes/${Uri.encode(livroId)}"
}
