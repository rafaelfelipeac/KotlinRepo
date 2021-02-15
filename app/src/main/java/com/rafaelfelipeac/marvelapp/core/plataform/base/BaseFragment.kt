package com.rafaelfelipeac.marvelapp.core.plataform.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.rafaelfelipeac.marvelapp.core.di.AppComponent
import com.rafaelfelipeac.marvelapp.core.di.AppComponentProvider
import com.rafaelfelipeac.marvelapp.core.di.provider.ViewModelProvider
import com.rafaelfelipeac.marvelapp.features.main.MainActivity

open class BaseFragment : Fragment() {

    private val main: MainActivity get() = (activity as MainActivity)
    val navController: NavController? get() = main.navController

    private val appComponent: AppComponent
        get() = (activity?.application as AppComponentProvider).appComponent
    val viewModelProvider: ViewModelProvider
        get() = appComponent

    fun hideBackArrow() {
        main.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun showBackArrow() {
        main.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setTitle(title: String?) {
        main.supportActionBar?.title = title ?: ""
    }
}
