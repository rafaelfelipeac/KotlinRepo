package com.rafaelfelipeac.kotlinrepo.features.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rafaelfelipeac.kotlinrepo.core.extension.viewBinding
import com.rafaelfelipeac.kotlinrepo.databinding.FragmentMainBinding
import com.rafaelfelipeac.kotlinrepo.features.favorite.presentation.FavoriteFragment
import com.rafaelfelipeac.kotlinrepo.features.repositories.presentation.RepositoriesFragment

class MainFragment : Fragment() {

    private var binding by viewBinding<FragmentMainBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMainBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.adapter = MainAdapter(parentFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.pager)
    }
}

class MainAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> RepositoriesFragment()
            1 -> FavoriteFragment()
            else -> RepositoriesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            0 -> "Characters"
            1 -> "Favorites"
            else -> ""
        }
    }
}
