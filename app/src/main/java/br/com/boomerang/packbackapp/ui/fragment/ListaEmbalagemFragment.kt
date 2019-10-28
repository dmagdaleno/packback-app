package br.com.boomerang.packbackapp.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Embalagem
import br.com.boomerang.packbackapp.ui.adapter.EmbalagemAdapter
import kotlinx.android.synthetic.main.fragment_lista_embalagem.view.*

class ListaEmbalagemFragment(idUsuario: Long) : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_lista_embalagem, container, false)

        val embalagens = listOf(
                Embalagem(descricao = "Caixa lanche", peso = 0.05),
                Embalagem(descricao = "Copo Plastico 500 ml", peso = 0.10)
        )

        view.lista_embalagem.adapter = EmbalagemAdapter(context!!, embalagens)

        return view
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
