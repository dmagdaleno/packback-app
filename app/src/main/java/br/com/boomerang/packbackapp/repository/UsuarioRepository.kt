package br.com.boomerang.packbackapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import br.com.boomerang.packbackapp.domain.Coleta
import br.com.boomerang.packbackapp.domain.Usuario
import br.com.boomerang.packbackapp.repository.local.UsuarioDao
import br.com.boomerang.packbackapp.repository.web.ColetaService
import br.com.boomerang.packbackapp.repository.web.UsuarioService
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioRepository (
        private val dao: UsuarioDao,
        private val usuarioService: UsuarioService,
        private val coletaService: ColetaService
) {

    companion object {
        private const val TAG = "UsuarioRepository"
    }

    fun getUsuario(id: Long): LiveData<Usuario> {
        atualizaUsuario(id)
        return dao.carrega(id)
    }

    private fun atualizaUsuario(id: Long) {

        usuarioService.getUsuario(id).enqueue(object : Callback<Usuario> {

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.e(TAG, "Erro ao buscar usuário $id", t)
            }

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                response.body()?.let { usuario ->
                    Log.d(TAG, "Encontrado usuário ${usuario.nome}")
                    doAsync {
                        dao.salva(usuario)
                        carregaColetas(id, usuario)
                    }
                }
            }

        })
    }

    private fun carregaColetas(id: Long, usuario: Usuario) {

        coletaService.getColetas(id).enqueue(object : Callback<List<Coleta>> {

            override fun onFailure(call: Call<List<Coleta>>, t: Throwable) {
                Log.e(TAG, "Erro ao buscar coletas do usuário $id", t)
            }

            override fun onResponse(call: Call<List<Coleta>>, response: Response<List<Coleta>>) {
                response.body()?.let { coletas ->
                    Log.d(TAG, "Coletas encontradas $coletas")

                    val pesoTotal = getPesoTotal(coletas)

                    val usuarioAtualizado = usuario.copy(totalColetas = coletas.size, totalPesoColetas = pesoTotal)

                    doAsync { dao.salva(usuarioAtualizado) }
                }
            }
        })
    }

    private fun getPesoTotal(coletas: List<Coleta>): Double {
        val pesos = coletas.map { it.embalagem.peso }
        return if (pesos.isNotEmpty())
            pesos.reduce { acc, peso -> acc + peso }
        else
            0.0
    }
}
