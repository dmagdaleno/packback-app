package br.com.boomerang.packbackapp.ui.activity

import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Produto
import br.com.boomerang.packbackapp.repository.web.PackbackService
import br.com.boomerang.packbackapp.repository.web.ProdutoService
import br.com.boomerang.packbackapp.ui.adapter.ProdutoAdapter
import br.com.boomerang.packbackapp.util.getIdRegiao
import kotlinx.android.synthetic.main.activity_lista_produto.*
import org.altbeacon.beacon.BeaconConsumer
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Region
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaProdutoActivity : AppCompatActivity(), BeaconConsumer {

    companion object {
        private const val TAG = "ListaProdutoActivity"
        private const val defaultTitle = "Produtos"
    }

    private lateinit var beaconManager: BeaconManager
    private var ultimaRegiao = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produto)
        title = defaultTitle

        beaconManager = BeaconManager.getInstanceForApplication(this)
        val beaconParser = BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT)
        beaconManager.beaconParsers.add(beaconParser)
        beaconManager.bind(this)
    }

    override fun onBeaconServiceConnect() {
        beaconManager.setRangeNotifier { beacons, _ ->
            if (beacons.isNotEmpty()) {
                val id = beacons.first().getIdRegiao()
                if(id != ultimaRegiao) {
                    ultimaRegiao = id
                    buscaProdutosPorRegiao(id)
                }
            }
        }

        try {
            beaconManager.startRangingBeaconsInRegion(Region("myRangingUniqueId", null, null, null))
        } catch (e: RemoteException) {
            Log.e(TAG, "Erro: ", e)
        }

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

    override fun onDestroy() {
        super.onDestroy()

        if(::beaconManager.isInitialized) {
            beaconManager.unbind(this)
        }
    }
}
