package com.rafaelfelipeac.kotlinrepositories

import android.app.Application
import com.rafaelfelipeac.kotlinrepositories.core.di.AppComponent
import com.rafaelfelipeac.kotlinrepositories.core.di.AppComponentProvider
import com.rafaelfelipeac.kotlinrepositories.core.di.DaggerAppComponent
import com.rafaelfelipeac.kotlinrepositories.core.network.NetworkMonitor

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
