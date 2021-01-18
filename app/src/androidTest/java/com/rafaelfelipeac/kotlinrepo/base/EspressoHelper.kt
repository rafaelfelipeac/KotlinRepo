package com.rafaelfelipeac.kotlinrepo.base

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import com.rafaelfelipeac.kotlinrepo.features.repositories.presentation.RepositoriesFragment

open class EspressoHelper {

    fun getDecorView(scenario: FragmentScenario<RepositoriesFragment>): View {
        var decorView: View? = null

        scenario.onFragment {
            decorView = it.activity?.window?.decorView
        }

        return decorView as View
    }
}