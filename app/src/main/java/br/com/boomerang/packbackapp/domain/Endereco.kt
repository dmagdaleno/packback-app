package br.com.boomerang.packbackapp.domain

data class Endereco (
        val titulo: String = "indefinido",
        val rua: String = "indefinido",
        val numero: Int = 0
) {

    val formatado = "$rua, $numero"
}