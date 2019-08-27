package br.com.boomerang.packbackapp.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.joda.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class Coleta (
        val id: Long? = null,
        val usuarioOrigem: Usuario = Usuario(),
        val usuarioDestino: Usuario = Usuario(),
        val embalagem: Embalagem = Embalagem(),
        val data: String = "indefinido"
) {
    fun getDataFormatada(): String {
        return LocalDateTime(data).toString("dd/MM/yyyy")
    }
}