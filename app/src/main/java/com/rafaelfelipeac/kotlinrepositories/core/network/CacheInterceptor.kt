package com.rafaelfelipeac.kotlinrepositories.core.network

import com.rafaelfelipeac.kotlinrepositories.core.plataform.Config.isNetworkConnected
import okhttp3.Interceptor

const val CACHE_MAX_AGE = 5000
const val CACHE_MAX_STALE = 60 * 60 * 24 * 7

class CacheInterceptor {

    var onlineInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = CACHE_MAX_AGE // read from cache for 5000 seconds even if there is internet connection

        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    var offlineInterceptor = Interceptor { chain ->
        var request = chain.request()

        if (!isNetworkConnected) {
            val maxStale = CACHE_MAX_STALE // Offline cache available for 7 days

            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }

        chain.proceed(request)
    }
}
