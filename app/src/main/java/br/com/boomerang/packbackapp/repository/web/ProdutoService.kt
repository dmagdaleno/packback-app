package br.com.boomerang.packbackapp.repository.web

import br.com.boomerang.packbackapp.domain.Produto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProdutoService {

    @GET("produtos/regiao/{id}")
    fun getProdutosPorRegiao(@Path("id") id: Long): Call<List<Produto>>
}