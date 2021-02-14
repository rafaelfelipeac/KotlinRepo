package com.rafaelfelipeac.marvelapp.features.characters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.MenuInflater
import android.view.MenuItem
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
import com.rafaelfelipeac.marvelapp.databinding.FragmentCharactersBinding
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.main.MainFragmentDirections

var CURRENT_PAGE = 1

@Suppress("TooManyFunctions")
class CharactersFragment : BaseFragment() {

    private var binding by viewBinding<FragmentCharactersBinding>()

    var viewModel: CharactersViewModel? = null

    private var characterAdapter = CharacterAdapter()

    private var isFirstPage = true
    private var isLoading = false

    private var listAsGrid = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setScreen()

        return FragmentCharactersBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    private fun setScreen() {
        hideBackArrow()
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.charactersViewModel()
        }

        setLayout()

        showList()

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()

        viewModel?.getCharacters()
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

    private fun setLayout() {
        binding.charactersList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    isLoading = true

                    CURRENT_PAGE++

                    // load the next page
                    // viewModel?.getCharacters(LANGUAGE, SORT, CURRENT_PAGE)

                    binding.charactersListLoader.visible()
                    binding.charactersList.scrollToPosition(characterAdapter.itemCount - 1)
                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel?.characters?.observe(viewLifecycleOwner) {
            isLoading = false

            showList()

            setList(it)
        }

        viewModel?.error?.observe(viewLifecycleOwner) {
            if (isLoading) {
                isLoading = false
                CURRENT_PAGE--
            }

            showPlaceholder()
        }

        viewModel?.savedFavorite?.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), "Favorited!", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setList(characters: List<Character>?) {
        characterAdapter.clearItems()
        characterAdapter.setItems(characters)
        characterAdapter.clickListener = { characterId ->
            val action = MainFragmentDirections.mainToDetail()
            action.characterId = characterId
            navController?.navigate(action)
        }
        characterAdapter.favoriteListener = {
            viewModel?.saveFavorite(it.id, it.name, it.thumbnail.getUrl())
        }

        if (true) {
            isFirstPage = false

            binding.charactersList.apply {
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

    private fun refreshList() {
        binding.charactersList.apply {
            setHasFixedSize(true)

            layoutManager = if (listAsGrid) {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }

            adapter = characterAdapter
        }
    }

    private fun showList() {
        binding.charactersList.visible()
        binding.charactersPlaceholder.gone()
        binding.charactersListLoader.gone()
        binding.charactersProgressBar.gone()
    }

    private fun showPlaceholder() {
        if ((!binding.charactersList.isVisible || binding.charactersProgressBar.isVisible)) {
            binding.charactersPlaceholder.visible()
        }

        binding.charactersListLoader.gone()
        binding.charactersProgressBar.gone()
    }

    private fun showProgressBar() {
        binding.charactersList.gone()
        binding.charactersPlaceholder.gone()
        binding.charactersListLoader.gone()
        binding.charactersProgressBar.visible()
        binding.charactersList.scrollToPosition(0)
    }
}
