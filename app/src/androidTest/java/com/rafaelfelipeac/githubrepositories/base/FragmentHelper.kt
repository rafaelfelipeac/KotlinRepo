package com.rafaelfelipeac.githubrepositories.base

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import com.rafaelfelipeac.githubrepositories.features.repositories.presentation.RepositoriesFragment

open class FragmentHelper {

    fun getDecorView(scenario: FragmentScenario<RepositoriesFragment>): View {
        var decorView: View? = null

        scenario.onFragment {
            decorView = it.activity?.window?.decorView
        }

        return decorView as View
    }
}
