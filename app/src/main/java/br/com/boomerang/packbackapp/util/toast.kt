package br.com.boomerang.packbackapp.util

import android.content.Context
import android.widget.Toast
import org.jetbrains.anko.runOnUiThread

fun toast(context: Context, msg: String) {
    context.runOnUiThread {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

fun funcionalidadeIndisponivel(context: Context) {
    toast(context, "Funcionalidade indisponível")
}