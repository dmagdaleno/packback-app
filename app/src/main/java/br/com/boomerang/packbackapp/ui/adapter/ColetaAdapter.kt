package br.com.boomerang.packbackapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Coleta
import kotlinx.android.synthetic.main.item_coleta.view.*
import kotlinx.android.synthetic.main.item_embalagem.view.*
import org.joda.time.format.DateTimeFormatter

class ColetaAdapter (
        private val context: Context,
        private val coletas: List<Coleta>): BaseAdapter() {

    override fun getView(posicao: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater
                .from(context)
                .inflate(R.layout.item_coleta, parent, false)

        val coleta = coletas[posicao]

        view.coleta_nome_empresa.text = coleta.usuarioDestino.razaoSocial
        view.coleta_data.text = coleta.getDataFormatada()
        view.coleta_peso.text = coleta.embalagem.getPesoFormatado()

        return view
    }

    override fun getItem(posicao: Int) = coletas[posicao]

    override fun getItemId(posicao: Int) = posicao.toLong()

    override fun getCount() = coletas.size
}