package boomerang.com.br.packbackapp.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import boomerang.com.br.packbackapp.domain.Usuario

@Dao
interface UsuarioDao {

    @Insert(onConflict = REPLACE)
    fun salva(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE id = :id")
    fun carrega(id: Long): LiveData<Usuario>
}