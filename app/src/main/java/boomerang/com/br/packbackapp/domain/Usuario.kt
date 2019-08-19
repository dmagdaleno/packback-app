package boomerang.com.br.packbackapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario (
        @PrimaryKey
        val id: Long,

        val email: String,

        val nome: String
)