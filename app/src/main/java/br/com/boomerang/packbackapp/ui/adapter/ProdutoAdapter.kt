package br.com.boomerang.packbackapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.boomerang.packbackapp.R
import br.com.boomerang.packbackapp.domain.Produto
import br.com.boomerang.packbackapp.util.formata
import kotlinx.android.synthetic.main.item_produto.view.*

class ProdutoAdapter (
        private val context: Context,
        private val produtos: List<Produto>): BaseAdapter() {

    override fun getView(posicao: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater
                .from(context)
                .inflate(R.layout.item_produto, parent, false)

        val produto = produtos[posicao]

        view.produto_descricao.text = produto.descricao
        view.produto_valor.text = formataValor(produto.valor)

        return view
    }

    private fun formataValor(valor: Double) = "R$ ${valor.formata()}"

    override fun getItem(posicao: Int) = produtos[posicao]

    override fun getItemId(posicao: Int) = posicao.toLong()

    override fun getCount() = produtos.size
}