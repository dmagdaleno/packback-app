package br.com.boomerang.packbackapp.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Produto
import br.com.boomerang.packbackapp.repository.web.PackbackService
import br.com.boomerang.packbackapp.repository.web.ProdutoService
import br.com.boomerang.packbackapp.ui.adapter.ProdutoAdapter
import kotlinx.android.synthetic.main.activity_lista_produto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaProdutoActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ListaProdutoActivity"
        private const val defaultTitle = "Produtos"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produto)
        title = defaultTitle

        // TODO recuperar o Id da região através do beacon
        val idRegiao = 4L

        buscaProdutosPorRegiao(idRegiao)

    }

    private fun buscaProdutosPorRegiao(id: Long) {
        PackbackService().cria(ProdutoService::class.java)?.let { service ->
            service.getProdutosPorRegiao(id).enqueue(object : Callback<List<Produto>> {

                override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
                    Log.e(TAG, "Erro ao buscar produtos", t)
                }

                override fun onResponse(
                        call: Call<List<Produto>>,
                        response: Response<List<Produto>>
                ) {
                    response.body()?.let {produtos ->
                        Log.d(TAG, "Produtos encontrados $produtos")
                        this@ListaProdutoActivity.title = parseTitle(produtos)
                        lista_produto.adapter = ProdutoAdapter(this@ListaProdutoActivity, produtos)
                    }
                }

            })
        }
    }

    private fun parseTitle(produtos: List<Produto>): String {
        if(produtos.isEmpty()) return defaultTitle

        return "$defaultTitle - Setor de ${produtos.first().regiao.setor}"
    }
}
