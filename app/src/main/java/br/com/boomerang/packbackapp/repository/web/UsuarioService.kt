package br.com.boomerang.packbackapp.repository.web

import br.com.boomerang.packbackapp.domain.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuarioService {

    @GET("usuarios/{id}")
    fun getUsuario(@Path("id") id: Long): Call<Usuario>
}