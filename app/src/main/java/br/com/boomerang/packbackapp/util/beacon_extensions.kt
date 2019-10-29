package br.com.boomerang.packbackapp.util

import org.altbeacon.beacon.Beacon

fun Beacon.getIdRegiao(): Long {
    val bigHexString = this.id2.toHexString()

    if(bigHexString.length < 3) {
        return 0
    }

    return bigHexString.substring(2).toLong()
}