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

    private var offset = 0

    private var binding by viewBinding<FragmentCharactersBinding>()

    var viewModel: CharactersViewModel? = null

    private var characterAdapter = CharacterAdapter()

    private var isFirstPage = true
    private var isLoading = false

    private var contentAsList = false

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

        isFirstPage = true

        viewModel?.getCharacters(offset)
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

    private fun setLayout() {
        binding.charactersList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    isLoading = true

                    // load the next page
                     viewModel?.getCharacters(offset)

                    binding.charactersListLoader.visible()
                    binding.charactersList.scrollToPosition(characterAdapter.itemCount - 1)
                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel?.characters?.observe(viewLifecycleOwner) {
            offset += 20

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
            Snackbar.make(requireView(), getString(R.string.characters_added_favorite), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setList(characters: List<Character>?) {
        characterAdapter.setItems(characters)
        characterAdapter.clickListener = { characterId ->
            val action = MainFragmentDirections.mainToDetail()
            action.characterId = characterId
            navController?.navigate(action)
        }
        characterAdapter.favoriteListener = {
            viewModel?.favoriteCharacter(it.id, it.name, it.thumbnail.getUrl())
        }

        if (isFirstPage) {
            isFirstPage = false

            binding.charactersList.apply {
                setHasFixedSize(true)

                layoutManager = if (contentAsList) {
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

            layoutManager = if (contentAsList) {
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
