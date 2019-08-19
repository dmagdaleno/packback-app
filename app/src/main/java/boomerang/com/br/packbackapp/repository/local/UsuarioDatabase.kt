package boomerang.com.br.packbackapp.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import boomerang.com.br.packbackapp.domain.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class UsuarioDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
}