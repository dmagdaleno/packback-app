package br.com.boomerang.packbackapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Endereco
import kotlinx.android.synthetic.main.item_embalagem.view.*
import kotlinx.android.synthetic.main.item_endereco.view.*

class EnderecoAdapter (
        private val context: Context,
        private val enderecos: List<Endereco>): BaseAdapter() {

    override fun getView(posicao: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater
                .from(context)
                .inflate(R.layout.item_endereco, parent, false)

        val endereco = enderecos[posicao]

        view.endereco_titulo.text = endereco.titulo
        view.endereco_descricao.text = endereco.formatado

        return view
    }

    override fun getItem(posicao: Int) = enderecos[posicao]

    override fun getItemId(posicao: Int) = posicao.toLong()

    override fun getCount() = enderecos.size
}