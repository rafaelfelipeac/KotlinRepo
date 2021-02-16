package com.rafaelfelipeac.marvelapp.core.plataform

import android.util.Log
import kotlin.properties.Delegates

object Config {

    const val URL_BASE_MARVEL = "http://gateway.marvel.com/v1/public/"

    const val API_KEY = "b9e0724953762441dc3acb362a9e723c"
    const val PRIVATE_KEY = "e6f6e7312ec620559005e10fc7d9829a23c04bd7"

    var isNetworkConnected: Boolean by Delegates.observable(false) { _, _, newValue ->
        Log.i("NetworkConnectivity", "Status = $newValue")
    }

    var refreshCharacter: Boolean = false
    var refreshFavorite: Boolean = false
}
