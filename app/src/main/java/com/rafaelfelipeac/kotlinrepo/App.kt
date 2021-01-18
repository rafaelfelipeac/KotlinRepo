package com.rafaelfelipeac.kotlinrepo

import android.app.Application
import com.rafaelfelipeac.kotlinrepo.core.di.AppComponent
import com.rafaelfelipeac.kotlinrepo.core.di.AppComponentProvider
import com.rafaelfelipeac.kotlinrepo.core.di.DaggerAppComponent
import com.rafaelfelipeac.kotlinrepo.core.network.NetworkMonitor

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
