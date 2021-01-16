package com.rafaelfelipeac.githubrepositories

import android.app.Application
import com.rafaelfelipeac.githubrepositories.core.di.AppComponent
import com.rafaelfelipeac.githubrepositories.core.di.AppComponentProvider
import com.rafaelfelipeac.githubrepositories.core.di.DaggerAppComponent
import com.rafaelfelipeac.githubrepositories.core.network.NetworkMonitor

class App : Application(), AppComponentProvider {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(application = this)
    }

    override fun onCreate() {
        super.onCreate()

        NetworkMonitor(this).startNetworkCallback()
    }
}
