package com.rafaelfelipeac.marvelapp.core.di

import android.app.Application
import com.rafaelfelipeac.marvelapp.core.di.modules.CommonsModule
import com.rafaelfelipeac.marvelapp.core.di.modules.CoreModule
import com.rafaelfelipeac.marvelapp.core.di.modules.PersistenceModule
import com.rafaelfelipeac.marvelapp.core.di.modules.PreferencesModule
import com.rafaelfelipeac.marvelapp.core.di.modules.CharactersModule
import com.rafaelfelipeac.marvelapp.core.di.modules.DetailsModule
import com.rafaelfelipeac.marvelapp.core.di.modules.FavoriteModule
import com.rafaelfelipeac.marvelapp.core.di.modules.NetworkModule
import com.rafaelfelipeac.marvelapp.core.di.provider.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        CharactersModule::class,
        DetailsModule::class,
        FavoriteModule::class,
        NetworkModule::class,
        CommonsModule::class,
        CoreModule::class,
        PersistenceModule::class,
        PreferencesModule::class
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
