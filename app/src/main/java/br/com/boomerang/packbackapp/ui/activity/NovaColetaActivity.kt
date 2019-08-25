package br.com.boomerang.packbackapp.ui.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.ui.fragment.ListaEmbalagemFragment

class NovaColetaActivity : AppCompatActivity(), ListaEmbalagemFragment.OnFragmentInteractionListener {

    companion object {
        private const val TAG = "NovaColetaActivity"
    }

    override fun onFragmentInteraction(uri: Uri) {
        Log.d(TAG, "onFragmentInteraction: $uri")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_coleta)

        val tx = supportFragmentManager.beginTransaction()
        tx.replace(R.id.frame_embalagens, ListaEmbalagemFragment())

        tx.commit()
    }
}
