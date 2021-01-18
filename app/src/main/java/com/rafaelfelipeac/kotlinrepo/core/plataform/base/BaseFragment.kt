package com.rafaelfelipeac.kotlinrepo.core.plataform.base

import androidx.fragment.app.Fragment
import com.rafaelfelipeac.kotlinrepo.core.di.AppComponent
import com.rafaelfelipeac.kotlinrepo.core.di.AppComponentProvider
import com.rafaelfelipeac.kotlinrepo.core.di.provider.ViewModelProvider

open class BaseFragment : Fragment() {

    private val appComponent: AppComponent
        get() = (activity?.application as AppComponentProvider).appComponent
    val viewModelProvider: ViewModelProvider
        get() = appComponent
}
