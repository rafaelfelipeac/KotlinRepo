package com.rafaelfelipeac.marvelapp.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.extension.viewBinding
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseFragment
import com.rafaelfelipeac.marvelapp.databinding.FragmentMainBinding
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharactersFragment
import com.rafaelfelipeac.marvelapp.features.favorites.presentation.FavoriteFragment

class MainFragment : BaseFragment() {

    private var binding by viewBinding<FragmentMainBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setScreen()

        return FragmentMainBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.adapter = MainAdapter(childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.pager)
    }

    private fun setScreen() {
        setTitle(getString(R.string.app_name))
        hideBackArrow()
    }
}

class MainAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CharactersFragment()
            1 -> FavoriteFragment()
            else -> CharactersFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Characters"
            1 -> "Favorites"
            else -> ""
        }
    }
}
