package br.com.boomerang.packbackapp.domain

import br.com.boomerang.packbackapp.util.formata
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Embalagem (
        val descricao: String = "indefinido",
        val peso: Double = 0.0,
        val volume: Double = 0.0,
        val valor: Double = 0.0
) {
    fun getPesoFormatado() = "${peso.formata()} kg"
}