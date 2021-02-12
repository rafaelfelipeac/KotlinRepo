package com.rafaelfelipeac.marvelapp.base

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharactersFragment

open class EspressoHelper {

    fun getDecorView(scenario: FragmentScenario<CharactersFragment>): View {
        var decorView: View? = null

        scenario.onFragment {
            decorView = it.activity?.window?.decorView
        }

        return decorView as View
    }
}
