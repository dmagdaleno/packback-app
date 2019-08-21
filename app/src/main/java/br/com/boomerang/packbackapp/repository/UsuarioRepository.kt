package br.com.boomerang.packbackapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import br.com.boomerang.packbackapp.domain.Usuario
import br.com.boomerang.packbackapp.repository.local.UsuarioDao
import br.com.boomerang.packbackapp.repository.web.UsuarioService
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioRepository (
        private val service: UsuarioService,
        private val dao: UsuarioDao
) {

    companion object {
        private const val TAG = "UsuarioRepository"
    }

    fun getUsuario(id: Long): LiveData<Usuario> {
        atualizaUsuario(id)
        return dao.carrega(id)
    }

    private fun atualizaUsuario(id: Long) {

        service.getUsuario(id).enqueue(object : Callback<Usuario> {

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.e(TAG, "Erro ao buscar usuário", t)
            }

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                response.body()?.let { usuario ->
                    Log.d(TAG, "Encontrado usuário ${usuario.nome}")
                    doAsync { dao.salva(usuario) }
                }
            }

        })
    }
}
