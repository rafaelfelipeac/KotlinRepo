package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.extension.gone
import com.rafaelfelipeac.marvelapp.core.extension.viewBinding
import com.rafaelfelipeac.marvelapp.core.extension.visible
import com.rafaelfelipeac.marvelapp.core.plataform.Config.refreshCharacter
import com.rafaelfelipeac.marvelapp.core.plataform.Config.refreshFavorite
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseFragment
import com.rafaelfelipeac.marvelapp.databinding.FragmentFavoriteBinding
import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite
import com.rafaelfelipeac.marvelapp.features.main.MainFragmentDirections

@Suppress("TooManyFunctions")
class FavoriteFragment : BaseFragment() {

    var viewModel: FavoritesViewModel? = null

    private var binding by viewBinding<FragmentFavoriteBinding>()

    private var favoriteAdapter = FavoriteAdapter()

    private var contentAsList: Boolean? = null
    private var deleteListener = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setScreen()

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

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()

        viewModel?.getListMode()
        viewModel?.getFavorites()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (contentAsList != null) {
            if (contentAsList != true) {
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
                refreshFavorite = true
                refreshCharacter = true

                viewModel?.saveListMode(contentAsList != true)

                return true
            }
        }

        return false
    }

    private fun setScreen() {
        hideBackArrow()
        setHasOptionsMenu(true)
    }

    private fun observeViewModel() {
        viewModel?.favorites?.observe(viewLifecycleOwner) {
            if (it?.size == 0) {
                showPlaceholder()
            } else {
                showList()
            }

            setList(it)
        }

        viewModel?.error?.observe(viewLifecycleOwner) {
            showPlaceholder()
        }

        viewModel?.deleted?.observe(viewLifecycleOwner) {
            if (deleteListener) {
                deleteListener = false
                Toast.makeText(
                    requireContext(),
                    getString(R.string.favorite_deleted),
                    Toast.LENGTH_SHORT
                ).show()

                viewModel?.getFavorites()
            }
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
            deleteListener = true
        }
        favoriteAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        if (binding.favoriteList.layoutManager == null) {
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

    private fun refreshList() {
        if (binding.favoriteList.layoutManager == null || refreshFavorite) {
            refreshFavorite = false
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
}
