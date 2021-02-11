package com.rafaelfelipeac.marvelapp

import android.app.Application
import com.rafaelfelipeac.marvelapp.core.di.AppComponent
import com.rafaelfelipeac.marvelapp.core.di.AppComponentProvider
import com.rafaelfelipeac.marvelapp.core.di.DaggerAppComponent
import com.rafaelfelipeac.marvelapp.core.network.NetworkMonitor

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
