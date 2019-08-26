package br.com.boomerang.packbackapp.util

fun Double.formata(digitos: Int = 2) = String.format("%.${digitos}f", this)