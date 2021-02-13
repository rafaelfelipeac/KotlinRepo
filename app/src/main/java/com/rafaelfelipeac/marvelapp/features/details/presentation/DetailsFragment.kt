package com.rafaelfelipeac.marvelapp.features.details.presentation

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.extension.viewBinding
import com.rafaelfelipeac.marvelapp.core.plataform.Config.API_KEY
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseFragment
import com.rafaelfelipeac.marvelapp.databinding.FragmentDetailsBinding
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Thumbnail
import com.rafaelfelipeac.marvelapp.features.main.MainFragmentDirections

class DetailsFragment : BaseFragment() {

    private var binding by viewBinding<FragmentDetailsBinding>()

    var viewModel: DetailsViewModel? = null

    private var comicsAdapter = DetailsInfoAdapter()
    private var seriesAdapter = DetailsInfoAdapter()

    private var listAsGrid = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setScreen()

        setHasOptionsMenu(false)

        return FragmentDetailsBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    private fun setScreen() {
        showBackArrow()
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.detailsViewModel()
        }

        setLayout()

        showList()

        setComics(
            listOf(
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", ""))
            )
        )

        setSeries(
            listOf(
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", ""))
            )
        )

        viewModel?.getDetails(API_KEY, "", 1L)
        viewModel?.getDetailsComics(API_KEY, "", 1L)
        viewModel?.getDetailsSeries(API_KEY, "", 1L)

        observeViewModel()
    }

    private fun setComics(characters: List<Character>?) {
        comicsAdapter.setItems(characters)
        comicsAdapter.clickListener = { character ->
            val action = MainFragmentDirections.mainToDetail()
            action.characterId = character.id
            navController?.navigate(action)
        }

        if (true) {
//            isFirstPage = false

            binding.comicsList.apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

                adapter = comicsAdapter
            }
        }
    }

    private fun setSeries(characters: List<Character>?) {
        seriesAdapter.setItems(characters)
        seriesAdapter.clickListener = { character ->
            val action = MainFragmentDirections.mainToDetail()
            action.characterId = character.id
            navController?.navigate(action)
        }

        if (true) {
//            isFirstPage = false

            binding.seriesList.apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

                adapter = seriesAdapter
            }
        }
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
        setComics(
            listOf(
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", ""))
            )
        )

        setSeries(
            listOf(
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", "")),
                Character(1, "", Thumbnail("", ""))
            )
        )
    }

    private fun showList() {
//        binding.charactersList.visible()
//        binding.charactersPlaceholder.gone()
//        binding.charactersListLoader.gone()
//        binding.charactersProgressBar.gone()
    }

    private fun showPlaceholder() {
//        if ((!binding.charactersList.isVisible || binding.charactersProgressBar.isVisible)) {
//            binding.charactersPlaceholder.visible()
//        }
//
//        binding.charactersListLoader.gone()
//        binding.charactersProgressBar.gone()
    }

    private fun setLayout() {

    }

    private fun observeViewModel() {
        viewModel?.details?.observe(viewLifecycleOwner) {

        }

        viewModel?.comics?.observe(viewLifecycleOwner) {

        }

        viewModel?.series?.observe(viewLifecycleOwner) {

        }

        viewModel?.error?.observe(viewLifecycleOwner) {

        }

        viewModel?.errorComics?.observe(viewLifecycleOwner) {

        }

        viewModel?.errorSeries?.observe(viewLifecycleOwner) {

        }
    }
}
