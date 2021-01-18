package com.rafaelfelipeac.kotlinrepo.core.plataform

import android.util.Log
import kotlin.properties.Delegates

object Config {

    const val URL_BASE_GITHUB = "https://api.github.com/"

    const val LANGUAGE = "language:kotlin"
    const val SORT = "star"

    var isNetworkConnected: Boolean by Delegates.observable(false) { _, _, newValue ->
        Log.i("NetworkConnectivity", "Status = $newValue")
    }
}
