package br.com.boomerang.packbackapp.domain

data class Endereco (
        val titulo: String = "indefinido",
        val logradouro: String = "indefinido",
        val rua: String = "indefinido",
        val numero: Int = 0
) {

    val formatado = "$logradouro $rua, $numero"
}