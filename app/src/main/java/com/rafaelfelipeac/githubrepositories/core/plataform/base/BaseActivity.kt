package com.rafaelfelipeac.githubrepositories.core.plataform.base

import androidx.appcompat.app.AppCompatActivity
import com.rafaelfelipeac.githubrepositories.core.di.AppComponent
import com.rafaelfelipeac.githubrepositories.core.di.AppComponentProvider
import com.rafaelfelipeac.githubrepositories.core.di.provider.ViewModelProvider

open class BaseActivity : AppCompatActivity(){

    private val appComponent: AppComponent
        get() = (application as AppComponentProvider).appComponent
    val viewModelProvider: ViewModelProvider
        get() = appComponent

}
