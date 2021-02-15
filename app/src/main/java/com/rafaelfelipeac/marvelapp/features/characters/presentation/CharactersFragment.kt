package com.rafaelfelipeac.marvelapp.features.characters.presentation

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.extension.gone
import com.rafaelfelipeac.marvelapp.core.extension.viewBinding
import com.rafaelfelipeac.marvelapp.core.extension.visible
import com.rafaelfelipeac.marvelapp.core.plataform.Config.refreshCharacter
import com.rafaelfelipeac.marvelapp.core.plataform.Config.refreshFavorite
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseFragment
import com.rafaelfelipeac.marvelapp.databinding.FragmentCharactersBinding
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.main.MainFragmentDirections

@Suppress("TooManyFunctions")
class CharactersFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    var viewModel: CharactersViewModel? = null

    private var binding by viewBinding<FragmentCharactersBinding>()

    private var characterAdapter = CharacterAdapter()

    private var isFirstPage = true
    private var isLoading = false
    private var offset = 0
    private var contentAsList: Boolean? = null
    private var favoriteListener = false

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.charactersViewModel()
        }

        setLayout()

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()

        binding.charactersRefresh.setOnRefreshListener(this)

        isFirstPage = true

        viewModel?.getListMode()
        viewModel?.getCharacters(offset)
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
                refreshCharacter = true
                refreshFavorite = true

                viewModel?.saveListMode(contentAsList != true)

                return true
            }
        }

        return false
    }

    override fun onRefresh() {
        offset = 0
        characterAdapter.clearItems()

        viewModel?.getCharacters(offset)
    }

    private fun setScreen() {
        hideBackArrow()
        setHasOptionsMenu(true)
    }

    private fun setLayout() {
        binding.charactersRefresh.setOnRefreshListener(this)

        binding.charactersList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    isLoading = true

                    viewModel?.getCharacters(offset)

                    binding.charactersListLoader.visible()
                    binding.charactersList.scrollToPosition(characterAdapter.itemCount - 1)
                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel?.characters?.observe(viewLifecycleOwner) {
            if (it?.isNotEmpty() == true) {
                binding.charactersRefresh.isRefreshing = false

                offset += 20

                isLoading = false

                showList()

                setList(it)
            } else {
                showPlaceholder()
            }
        }

        viewModel?.error?.observe(viewLifecycleOwner) {
            binding.charactersRefresh.isRefreshing = false

            if (isLoading) {
                isLoading = false
            }

            showPlaceholder()
        }

        viewModel?.savedFavorite?.observe(viewLifecycleOwner) {
            if (favoriteListener) {
                favoriteListener = false
                Snackbar.make(
                    requireView(),
                    getString(R.string.characters_added_favorite),
                    Snackbar.LENGTH_SHORT
                ).show()
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

    private fun setList(characters: List<Character>?) {
        characterAdapter.setItems(characters)
        characterAdapter.clickListener = { characterId ->
            val action = MainFragmentDirections.mainToDetail()
            action.characterId = characterId
            navController?.navigate(action)
        }
        characterAdapter.favoriteListener = {
            viewModel?.favoriteCharacter(it.id, it.name, it.thumbnail.getUrl())
            favoriteListener = true
        }
        characterAdapter.stateRestorationPolicy =  RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        if (isFirstPage && binding.charactersList.layoutManager == null) {
            isFirstPage = false

            binding.charactersList.apply {
                setHasFixedSize(true)

                layoutManager = if (contentAsList == true) {
                    GridLayoutManager(context, 2)
                } else {
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }

                adapter = characterAdapter
            }
        }
    }

    private fun refreshList() {
        if (binding.charactersList.layoutManager == null || refreshCharacter) {
            refreshCharacter = false
            binding.charactersList.apply {
                setHasFixedSize(true)

                layoutManager = if (contentAsList == true) {
                    GridLayoutManager(context, 2)
                } else {
                    LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }

                adapter = characterAdapter
            }
        }
    }

    private fun showList() {
        binding.charactersList.visible()
        binding.charactersPlaceholder.gone()
        binding.charactersListLoader.gone()
        binding.charactersProgressBar.gone()
    }

    private fun showPlaceholder() {
        if (!binding.charactersList.isVisible) {
            binding.charactersPlaceholder.visible()
            binding.charactersListLoader.gone()
            binding.charactersProgressBar.gone()
        } else {
            binding.charactersListLoader.gone()
        }
    }
}
