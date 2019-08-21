package br.com.boomerang.packbackapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class Usuario (

        @PrimaryKey
        val id: Long? = null,

        val email: String = "Indefinido",

        val nome: String = "Indefinido",

        val pontos: Long = 0
)