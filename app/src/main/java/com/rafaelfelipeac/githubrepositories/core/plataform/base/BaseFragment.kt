package com.rafaelfelipeac.githubrepositories.core.plataform.base

import androidx.fragment.app.Fragment
import com.rafaelfelipeac.githubrepositories.core.di.provider.ViewModelProvider

open class BaseFragment : Fragment() {

    protected val viewModelProvider: ViewModelProvider
        get() = (activity as BaseActivity).viewModelProvider
}
