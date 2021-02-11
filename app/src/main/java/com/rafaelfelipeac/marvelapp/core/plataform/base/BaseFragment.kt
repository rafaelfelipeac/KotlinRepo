package com.rafaelfelipeac.marvelapp.core.plataform.base

import androidx.fragment.app.Fragment
import com.rafaelfelipeac.marvelapp.core.di.AppComponent
import com.rafaelfelipeac.marvelapp.core.di.AppComponentProvider
import com.rafaelfelipeac.marvelapp.core.di.provider.ViewModelProvider

open class BaseFragment : Fragment() {

    private val appComponent: AppComponent
        get() = (activity?.application as AppComponentProvider).appComponent
    val viewModelProvider: ViewModelProvider
        get() = appComponent
}
