package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.extension.gone
import com.rafaelfelipeac.marvelapp.core.extension.viewBinding
import com.rafaelfelipeac.marvelapp.core.extension.visible
import com.rafaelfelipeac.marvelapp.core.plataform.Config
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseFragment
import com.rafaelfelipeac.marvelapp.databinding.FragmentFavoriteBinding
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Owner
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CURRENT_PAGE
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharacterAdapter
import com.rafaelfelipeac.marvelapp.features.main.MainFragmentDirections

class FavoriteFragment : BaseFragment() {

    private var binding by viewBinding<FragmentFavoriteBinding>()

    var viewModel: FavoriteViewModel? = null

    private var characterAdapter = CharacterAdapter()

    private var listAsGrid = false

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

    private fun setScreen() {
        hideBackArrow()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_refresh, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuRefresh -> {
                listAsGrid = !listAsGrid

                refreshList()

                return true
            }
        }

        return false
    }

    private fun refreshList() {
        setList(
            listOf(
                Character(1, "", 1, 2, Owner("", "")),
                Character(1, "", 1, 2, Owner("", "")),
                Character(1, "", 1, 2, Owner("", ""))
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.favoriteViewModel()
        }

        setLayout()

        showList()

        setList(
            listOf(
                Character(1, "", 1, 2, Owner("", "")),
                Character(1, "", 1, 2, Owner("", "")),
                Character(1, "", 1, 2, Owner("", ""))
            )
        )

        viewModel?.getFavorites(Config.LANGUAGE, Config.SORT, CURRENT_PAGE)

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

    private fun setList(characters: List<Character>?) {
        characterAdapter.setItems(characters)
        characterAdapter.clickListener = { character ->
            val action = MainFragmentDirections.mainToDetail()
            action.characterId = character.id
            navController?.navigate(action)
        }

        if (true) {
            binding.favoriteList.apply {
                setHasFixedSize(true)

                layoutManager = if (listAsGrid) {
                    GridLayoutManager(context, 2)
                } else {
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }

                adapter = characterAdapter
            }
        }
    }

}
