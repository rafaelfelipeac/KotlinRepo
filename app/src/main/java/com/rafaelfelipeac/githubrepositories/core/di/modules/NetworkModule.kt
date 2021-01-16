package com.rafaelfelipeac.githubrepositories.core.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.rafaelfelipeac.githubrepositories.core.network.CacheInterceptor
import com.rafaelfelipeac.githubrepositories.core.network.NetworkMonitor
import com.rafaelfelipeac.githubrepositories.core.plataform.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.URL_GITHUB)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cacheInterceptor: CacheInterceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(cacheInterceptor.offlineInterceptor)
            .addNetworkInterceptor(cacheInterceptor.onlineInterceptor)
            .build()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun cache(
        context: Context
    ): Cache = Cache(context.cacheDir, (10 * 1024 * 1024).toLong()) // 10MB

    @Provides
    fun networkMonitor(
        application: Application
    ): NetworkMonitor = NetworkMonitor(application)
}
