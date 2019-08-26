package br.com.boomerang.packbackapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Embalagem
import br.com.boomerang.packbackapp.util.formata
import kotlinx.android.synthetic.main.item_embalagem.view.*

class EmbalagemAdapter (
        private val context: Context,
        private val embalagens: List<Embalagem>): BaseAdapter() {

    override fun getView(posicao: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater
                .from(context)
                .inflate(R.layout.item_embalagem, parent, false)

        val embalagem = embalagens[posicao]

        view.embalagem_descricao.text = embalagem.descricao
        view.embalagem_valor.text = formataValor(embalagem.valor)

        return view
    }

    private fun formataValor(valor: Double) = "R$ ${valor.formata()}"

    override fun getItem(posicao: Int) = embalagens[posicao]

    override fun getItemId(posicao: Int) = posicao.toLong()

    override fun getCount() = embalagens.size
}