package boomerang.com.br.packbackapp.repository.web

import boomerang.com.br.packbackapp.domain.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuarioService {

    @GET("/usuarios/{id}")
    fun getUsuario(@Path("id") id: Long): Call<Usuario>
}