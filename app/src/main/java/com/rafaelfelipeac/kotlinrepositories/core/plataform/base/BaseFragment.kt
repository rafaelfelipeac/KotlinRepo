package com.rafaelfelipeac.kotlinrepositories.core.plataform.base

import androidx.fragment.app.Fragment
import com.rafaelfelipeac.kotlinrepositories.core.di.AppComponent
import com.rafaelfelipeac.kotlinrepositories.core.di.AppComponentProvider
import com.rafaelfelipeac.kotlinrepositories.core.di.provider.ViewModelProvider

open class BaseFragment : Fragment() {

    private val appComponent: AppComponent
        get() = (activity?.application as AppComponentProvider).appComponent
    val viewModelProvider: ViewModelProvider
        get() = appComponent
}
