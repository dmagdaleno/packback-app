package br.com.boomerang.packbackapp.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.boomerang.packbackapp.domain.Usuario

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object {

        private const val NOME_BASE = "packback.db"

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if(instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            NOME_BASE)
                            .build()
                }
            }

            return instance!!
        }

        fun destroyInstance() {
            instance?.close()
            instance = null
        }
    }
}