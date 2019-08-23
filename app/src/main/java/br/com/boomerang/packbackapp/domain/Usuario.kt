package br.com.boomerang.packbackapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
data class Usuario (

        @PrimaryKey
        val id: Long? = null,

        val email: String = "Indefinido",

        val nome: String = "Indefinido",

        val foto: String = "@mipmap/ic_launcher_round",

        val dataCadastro: Date = Date(),

        val pontos: Long = 0
)