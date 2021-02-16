package com.rafaelfelipeac.marvelapp.core.plataform.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.rafaelfelipeac.marvelapp.core.di.AppComponent
import com.rafaelfelipeac.marvelapp.core.di.AppComponentProvider
import com.rafaelfelipeac.marvelapp.core.di.provider.ViewModelProvider
import com.rafaelfelipeac.marvelapp.core.extension.md5
import com.rafaelfelipeac.marvelapp.core.plataform.Config.API_KEY
import com.rafaelfelipeac.marvelapp.core.plataform.Config.PRIVATE_KEY
import com.rafaelfelipeac.marvelapp.features.main.MainActivity
import java.util.Date

open class BaseFragment : Fragment() {

    private val main: MainActivity get() = (activity as MainActivity)
    val navController: NavController? get() = main.navController

    private val appComponent: AppComponent
        get() = (activity?.application as AppComponentProvider).appComponent
    val viewModelProvider: ViewModelProvider
        get() = appComponent

    fun hideBackArrow() {
        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    fun showBackArrow() {
        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    fun setTitle(title: String?) {
        if (activity is MainActivity) {
            (activity as MainActivity).supportActionBar?.title = title ?: ""
        }
    }

    fun createTimestamp() = Date().time
    fun createHash(timestamp: Long) = (timestamp.toString() + PRIVATE_KEY + API_KEY).md5()
}
