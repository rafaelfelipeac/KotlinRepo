package com.rafaelfelipeac.marvelapp.features.characters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.extension.gone
import com.rafaelfelipeac.marvelapp.core.extension.viewBinding
import com.rafaelfelipeac.marvelapp.core.extension.visible
import com.rafaelfelipeac.marvelapp.core.plataform.Config.LANGUAGE
import com.rafaelfelipeac.marvelapp.core.plataform.Config.SORT
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseFragment
import com.rafaelfelipeac.marvelapp.databinding.FragmentCharactersBinding
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character

var CURRENT_PAGE = 1

@Suppress("TooManyFunctions")
class CharactersFragment : BaseFragment() {

    private var binding by viewBinding<FragmentCharactersBinding>()

    var viewModel: CharactersViewModel? = null

    private var characterAdapter = CharacterAdapter()

    private var isFirstPage = true
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        return FragmentCharactersBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.charactersViewModel()
        }

        setLayout()

        viewModel?.getCharacters(LANGUAGE, SORT, CURRENT_PAGE)

        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_refresh, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuRefresh -> {
                refreshCharacters()

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
                    viewModel?.getCharacters(LANGUAGE, SORT, CURRENT_PAGE)

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
    }

    private fun setList(characters: List<Character>?) {
        characterAdapter.setItems(characters)
        characterAdapter.clickListener = { character ->
            Toast.makeText(context, character.name, Toast.LENGTH_SHORT).show()
        }

        if (isFirstPage) {
            isFirstPage = false

            binding.charactersList.apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = characterAdapter
            }
        }
    }

    private fun refreshCharacters() {
        characterAdapter.clearItems()
        CURRENT_PAGE = 1

        showProgressBar()

        viewModel?.getCharacters(LANGUAGE, SORT, CURRENT_PAGE)
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
