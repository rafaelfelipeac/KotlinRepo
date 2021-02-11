package com.rafaelfelipeac.marvelapp.core.di

import android.app.Application
import com.rafaelfelipeac.marvelapp.core.di.modules.CoreModule
import com.rafaelfelipeac.marvelapp.core.di.modules.NetworkModule
import com.rafaelfelipeac.marvelapp.core.di.modules.RepositoriesModule
import com.rafaelfelipeac.marvelapp.core.di.provider.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RepositoriesModule::class,
        NetworkModule::class,
        CoreModule::class
    ]
)
@Singleton
interface AppComponent : ViewModelProvider {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}

interface AppComponentProvider {
    val appComponent: AppComponent
}
