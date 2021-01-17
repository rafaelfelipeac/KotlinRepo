package com.rafaelfelipeac.githubrepositories.core.plataform.base

import androidx.fragment.app.Fragment
import com.rafaelfelipeac.githubrepositories.core.di.AppComponent
import com.rafaelfelipeac.githubrepositories.core.di.AppComponentProvider
import com.rafaelfelipeac.githubrepositories.core.di.provider.ViewModelProvider

open class BaseFragment : Fragment() {

    private val appComponent: AppComponent
        get() = (activity?.application as AppComponentProvider).appComponent
    val viewModelProvider: ViewModelProvider
        get() = appComponent
}
