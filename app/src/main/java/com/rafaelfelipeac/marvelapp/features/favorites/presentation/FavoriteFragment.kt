package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import android.os.Bundle
import android.view.*
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

    private var contentAsList: Boolean? = null

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

        viewModel?.getListMode()
        viewModel?.getFavorites()
    }

    private fun setScreen() {
        hideBackArrow()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (contentAsList != null) {
            if (!contentAsList!!) {
                inflater.inflate(R.menu.menu_grid, menu)
            } else {
                inflater.inflate(R.menu.menu_list, menu)
            }
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuGrid, R.id.menuList -> {
                viewModel?.saveListMode(!contentAsList!!)

                return true
            }
        }

        return false
    }

    private fun refreshList() {
        binding.favoriteList.apply {
            setHasFixedSize(true)

            layoutManager = if (contentAsList == true) {
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
        binding.favoriteListLoader.visible()
    }

    private fun observeViewModel() {
        viewModel?.favorites?.observe(viewLifecycleOwner) {
            showList()

            setList(it)

            if (it?.size == 0) {
                showPlaceholder()
            }
        }

        viewModel?.error?.observe(viewLifecycleOwner) {
            showPlaceholder()
        }

        viewModel?.deleted?.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), getString(R.string.favorite_deleted), Snackbar.LENGTH_SHORT).show()

            viewModel?.getFavorites()
        }

        viewModel?.listMode?.observe(viewLifecycleOwner) {
            contentAsList = it ?: false

            refreshList()

            activity?.invalidateOptionsMenu()
        }

        viewModel?.savedListMode?.observe(viewLifecycleOwner) {
            viewModel?.getListMode()
        }
    }

    private fun showList() {
        binding.favoriteList.visible()
        binding.favoritePlaceholder.gone()
        binding.favoriteListLoader.gone()
        binding.favoriteProgressBar.gone()
    }

    private fun showPlaceholder() {
        binding.favoritePlaceholder.visible()
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

            layoutManager = if (contentAsList == true) {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }

            adapter = favoriteAdapter
        }
    }
}
