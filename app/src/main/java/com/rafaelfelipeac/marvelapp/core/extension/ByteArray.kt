package com.rafaelfelipeac.marvelapp.core.extension

fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }
