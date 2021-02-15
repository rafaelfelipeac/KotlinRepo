package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.extension.gone
import com.rafaelfelipeac.marvelapp.core.extension.viewBinding
import com.rafaelfelipeac.marvelapp.core.extension.visible
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseFragment
import com.rafaelfelipeac.marvelapp.databinding.FragmentFavoriteBinding
import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite
import com.rafaelfelipeac.marvelapp.features.main.MainFragmentDirections

class FavoriteFragment : BaseFragment() {

    private var binding by viewBinding<FragmentFavoriteBinding>()

    var viewModel: FavoriteViewModel? = null

    private var favoriteAdapter = FavoriteAdapter()

    private var contentAsList = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setScreen()

        return FragmentFavoriteBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel?.getFavorites()
    }

    private fun setScreen() {
        hideBackArrow()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (!contentAsList) {
            inflater.inflate(R.menu.menu_grid, menu)
        } else {
            inflater.inflate(R.menu.menu_list, menu)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuGrid -> {
                contentAsList = !contentAsList

                refreshList()

                activity?.invalidateOptionsMenu()

                return true
            }
            R.id.menuList -> {
                contentAsList = !contentAsList

                refreshList()

                activity?.invalidateOptionsMenu()

                return true
            }
        }

        return false
    }

    private fun refreshList() {
        binding.favoriteList.apply {
            setHasFixedSize(true)

            layoutManager = if (contentAsList) {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }

            adapter = favoriteAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.favoriteViewModel()
        }

        setLayout()

        showList()

        observeViewModel()
    }

    private fun setLayout() {

    }

    private fun observeViewModel() {
        viewModel?.favorites?.observe(viewLifecycleOwner) {
            showList()

            setList(it)
        }

        viewModel?.error?.observe(viewLifecycleOwner) {
            showPlaceholder()
        }

        viewModel?.deleted?.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), getString(R.string.favorite_deleted), Snackbar.LENGTH_SHORT).show()

            viewModel?.getFavorites()
        }
    }

    private fun showList() {
        binding.favoriteList.visible()
        binding.favoritePlaceholder.gone()
        binding.favoriteListLoader.gone()
        binding.favoriteProgressBar.gone()
    }

    private fun showPlaceholder() {
        if ((!binding.favoriteList.isVisible || binding.favoriteProgressBar.isVisible)) {
            binding.favoritePlaceholder.visible()
        }

        binding.favoriteListLoader.gone()
        binding.favoriteProgressBar.gone()
    }

    private fun setList(favorites: List<Favorite>?) {
        favoriteAdapter.clearItems()
        favoriteAdapter.setItems(favorites)
        favoriteAdapter.clickListener = { characterId ->
            val action = MainFragmentDirections.mainToDetail()
            action.characterId = characterId
            navController?.navigate(action)
        }
        favoriteAdapter.clickListenerFavorite = { favorite ->
            viewModel?.deleteFavorite(favorite)
        }

        binding.favoriteList.apply {
            setHasFixedSize(true)

            layoutManager = if (contentAsList) {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }

            adapter = favoriteAdapter
        }
    }

}
