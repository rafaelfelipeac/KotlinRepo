package com.rafaelfelipeac.githubrepositories.core.network

import com.rafaelfelipeac.githubrepositories.core.plataform.Constants
import com.rafaelfelipeac.githubrepositories.features.repositories.data.RepositoriesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoriesService {

    val api: RepositoriesApi = Retrofit.Builder()
        .baseUrl(Constants.URL_GITHUB)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()
        .create(RepositoriesApi::class.java)

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        okHttpClientBuilder.addInterceptor(logging)

        return okHttpClientBuilder.build()
    }
}
