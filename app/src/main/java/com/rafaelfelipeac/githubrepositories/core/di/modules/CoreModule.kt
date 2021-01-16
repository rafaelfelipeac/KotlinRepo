package com.rafaelfelipeac.githubrepositories.core.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.rafaelfelipeac.githubrepositories.features.repositories.data.RepositoriesApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
abstract class CoreModule {

    companion object {

        @Provides
        fun gson(): Gson = Gson()

        @Provides
        fun Retrofit.repositoriesApi(): RepositoriesApi = create()
    }

    @Binds
    abstract fun Application.context(): Context

}
