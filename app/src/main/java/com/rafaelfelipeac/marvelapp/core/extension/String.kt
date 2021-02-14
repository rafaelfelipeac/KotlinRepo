package com.rafaelfelipeac.marvelapp.core.extension

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

fun String.md5(): String = MessageDigest.getInstance("MD5").digest(
    this.toByteArray(
        StandardCharsets.UTF_8
    )
).toHex()
