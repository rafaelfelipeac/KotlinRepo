package com.rafaelfelipeac.githubrepositories.core.plataform

import android.util.Log
import kotlin.properties.Delegates

object Constants {

    const val URL_GITHUB = "https://api.github.com/"

    var isNetworkConnected: Boolean by Delegates.observable(false) { _, _, newValue ->
        Log.i("Network connectivity", "$newValue")
    }
}
