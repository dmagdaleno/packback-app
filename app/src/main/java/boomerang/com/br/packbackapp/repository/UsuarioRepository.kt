package boomerang.com.br.packbackapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import boomerang.com.br.packbackapp.domain.Usuario
import boomerang.com.br.packbackapp.repository.local.UsuarioDao
import boomerang.com.br.packbackapp.repository.web.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class UsuarioRepository (
        private val service: UsuarioService,
        private val dao: UsuarioDao
) {

    companion object {
        private const val TAG = "UsuarioRepository"
        private val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }

    fun getUsuario(id: Long): LiveData<Usuario> {
        atualizaUsuario(id)
        return dao.carrega(id)
    }

    private fun atualizaUsuario(id: Long) {

        val usuarioAtualizado = usuarioEstaAtualizado(id)

        if (!usuarioAtualizado) {

            service.getUsuario(1).enqueue(object : Callback<Usuario> {

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Log.e(TAG, "Erro ao buscar usuário", t)
                }

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                    val usuario = response.body()
                            ?: throw IllegalArgumentException("Usuário não encontrado")

                    Log.d(TAG, "Encontrado usuário ${usuario.nome}")

                    dao.salva(usuario)
                }

            })
        }
    }

    private fun usuarioEstaAtualizado(id: Long): Boolean {
        return dao.carrega(id).value != null
    }
}
