package br.com.boomerang.packbackapp.repository.web

import br.com.boomerang.packbackapp.domain.Login
import br.com.boomerang.packbackapp.domain.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login/autentica")
    fun autentica(@Body login: Login): Call<Usuario>
}