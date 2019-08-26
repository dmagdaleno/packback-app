package br.com.boomerang.packbackapp.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Coleta (
        val id: Long? = null,
        val usuarioOrigem: Usuario = Usuario(),
        val usuarioDestino: Usuario = Usuario(),
        val embalagem: Embalagem = Embalagem()
)