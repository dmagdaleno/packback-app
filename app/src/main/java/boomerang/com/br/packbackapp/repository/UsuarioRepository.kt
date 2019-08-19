package boomerang.com.br.packbackapp.repository

import androidx.lifecycle.LiveData
import boomerang.com.br.packbackapp.domain.Usuario
import boomerang.com.br.packbackapp.repository.local.UsuarioDao
import boomerang.com.br.packbackapp.repository.web.UsuarioService
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsuarioRepository @Inject constructor(
        private val usuarioService: UsuarioService,
        private val executor: Executor,
        private val usuarioDao: UsuarioDao
) {

    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }

    fun getUsuario(id: Long): LiveData<Usuario> {
        atualizaUsuario(id)
        return usuarioDao.carrega(id)
    }

    private fun atualizaUsuario(id: Long) {

        executor.execute {
            val usuarioAtualizado = usuarioEstaAtualizado(id)

            if (!usuarioAtualizado) {

                val response = usuarioService.getUsuario(id).execute()

                val usuario = response.body()
                        ?: throw IllegalStateException("Usuario não pode ser nulo")

                usuarioDao.salva(usuario)
            }
        }
    }

    private fun usuarioEstaAtualizado(id: Long): Boolean {
        return usuarioDao.carrega(id).value != null
    }
}
