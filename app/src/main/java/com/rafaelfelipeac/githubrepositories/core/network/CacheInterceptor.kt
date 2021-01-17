package com.rafaelfelipeac.githubrepositories.core.network

import com.rafaelfelipeac.githubrepositories.core.plataform.Config.isNetworkConnected
import okhttp3.Interceptor

class CacheInterceptor() {

    var onlineInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 5000 // read from cache for 5000 seconds even if there is internet connection

        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    var offlineInterceptor = Interceptor { chain ->
        var request = chain.request()

        if (!isNetworkConnected) {
            val maxStale = 60 * 60 * 24 * 7 // Offline cache available for 7 days

            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }

        chain.proceed(request)
    }
}
