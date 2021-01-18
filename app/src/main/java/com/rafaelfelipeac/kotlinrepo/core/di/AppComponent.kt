package com.rafaelfelipeac.kotlinrepo.core.di

import android.app.Application
import com.rafaelfelipeac.kotlinrepo.core.di.modules.CoreModule
import com.rafaelfelipeac.kotlinrepo.core.di.modules.NetworkModule
import com.rafaelfelipeac.kotlinrepo.core.di.modules.RepositoriesModule
import com.rafaelfelipeac.kotlinrepo.core.di.provider.ViewModelProvider
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
