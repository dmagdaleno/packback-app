package br.com.boomerang.packbackapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class Login (

        @PrimaryKey
        val email: String = "indefinido",

        val senha: String = "indefinido",

        val ultimaAutenticacao: Date = Date()
)