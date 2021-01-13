package com.rafaelfelipeac.githubrepositories.core.di

import android.app.Application
import com.rafaelfelipeac.githubrepositories.core.di.modules.RepositoriesModule
import com.rafaelfelipeac.githubrepositories.core.di.provider.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    RepositoriesModule::class,
])
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
