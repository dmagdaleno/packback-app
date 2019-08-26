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

        val email: String = "indefinido",

        val nome: String = "indefinido",

        val razaoSocial: String? = null,

        val dataCadastro: Date = Date(),

        val pontos: Long = 0,

        val totalColetas: Int = 0,

        val totalPesoColetas: Double = 0.0
) {
        fun getPesoColetasFormatado() = "$totalPesoColetas kg"
}