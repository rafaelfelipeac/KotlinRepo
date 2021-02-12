package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafaelfelipeac.marvelapp.core.extension.viewBinding
import com.rafaelfelipeac.marvelapp.core.plataform.Config
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseFragment
import com.rafaelfelipeac.marvelapp.databinding.FragmentFavoriteBinding
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CURRENT_PAGE

class FavoriteFragment : BaseFragment() {

    private var binding by viewBinding<FragmentFavoriteBinding>()

    var viewModel: FavoriteViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFavoriteBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.favoriteViewModel()
        }

        setLayout()

        viewModel?.getFavorites(Config.LANGUAGE, Config.SORT, CURRENT_PAGE)

        observeViewModel()
    }

    private fun setLayout() {

    }

    private fun observeViewModel() {
        viewModel?.favorites?.observe(viewLifecycleOwner) {

        }

        viewModel?.error?.observe(viewLifecycleOwner) {

        }
    }
}
