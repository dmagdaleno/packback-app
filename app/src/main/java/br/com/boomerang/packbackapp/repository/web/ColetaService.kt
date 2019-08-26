package br.com.boomerang.packbackapp.repository.web

import br.com.boomerang.packbackapp.domain.Coleta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ColetaService {

    @GET("movimentacoes/usuario/{id}")
    fun getColetas(@Path("id") id: Long): Call<List<Coleta>>
}