package br.com.boomerang.packbackapp.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Embalagem (
        val descricao: String = "indefinido",
        val peso: Double = 0.0,
        val valor: Double = 0.0
)