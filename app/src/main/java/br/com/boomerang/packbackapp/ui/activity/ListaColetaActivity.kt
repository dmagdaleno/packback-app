package br.com.boomerang.packbackapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Coleta
import br.com.boomerang.packbackapp.repository.UsuarioRepository
import br.com.boomerang.packbackapp.repository.web.ColetaService
import br.com.boomerang.packbackapp.repository.web.PackbackService
import br.com.boomerang.packbackapp.ui.Keys
import br.com.boomerang.packbackapp.ui.adapter.ColetaAdapter
import kotlinx.android.synthetic.main.activity_lista_coleta.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaColetaActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ListaColetaActivity"
    }

    private val idUsuario: Long by lazy { intent.extras.getLong(Keys.ID_USUARIO) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_coleta)

        PackbackService().cria(ColetaService::class.java)?.let { service ->
            service.getColetas(idUsuario).enqueue(object : Callback<List<Coleta>> {

                override fun onFailure(call: Call<List<Coleta>>, t: Throwable) {
                    Log.e(TAG, "Erro ao buscar coletas do usuário $idUsuario", t)
                }

                override fun onResponse(call: Call<List<Coleta>>, response: Response<List<Coleta>>) {
                    response.body()?.let { coletas ->
                        Log.d(TAG, "Coletas encontradas $coletas")
                        lista_coleta_list.adapter = ColetaAdapter(this@ListaColetaActivity, coletas)
                    }
                }

            })
        }

    }
}
