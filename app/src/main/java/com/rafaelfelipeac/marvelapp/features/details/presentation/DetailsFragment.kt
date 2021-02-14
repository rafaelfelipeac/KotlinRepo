package com.rafaelfelipeac.marvelapp.features.details.presentation

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.extension.viewBinding
import com.rafaelfelipeac.marvelapp.core.extension.visible
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseFragment
import com.rafaelfelipeac.marvelapp.databinding.FragmentDetailsBinding
import com.rafaelfelipeac.marvelapp.features.details.domain.model.DetailInfo
import com.rafaelfelipeac.marvelapp.features.main.MainFragmentDirections
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class DetailsFragment : BaseFragment() {

    private var binding by viewBinding<FragmentDetailsBinding>()

    var viewModel: DetailsViewModel? = null

    private var comicsAdapter = DetailsInfoAdapter()
    private var seriesAdapter = DetailsInfoAdapter()

    private var characterId: Long = 0L

    private var listAsGrid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        characterId = arguments?.let { DetailsFragmentArgs.fromBundle(it).characterId } ?: 0L
    }

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

        viewModel?.getDetails(characterId)
        viewModel?.getDetailsComics(characterId)
        viewModel?.getDetailsSeries(characterId)

        observeViewModel()
    }

    private fun setComics(comics: List<DetailInfo>?) {
        comicsAdapter.setItems(comics)
        comicsAdapter.clickListener = { character ->
            val action = MainFragmentDirections.mainToDetail()
            action.characterId = character.id
            navController?.navigate(action)
        }

        if (true) {
//            isFirstPage = false

            binding.detailsCharacterComicsList.apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

                adapter = comicsAdapter
            }
        }
    }

    private fun setSeries(series: List<DetailInfo>?) {
        seriesAdapter.setItems(series)
        seriesAdapter.clickListener = { character ->
            val action = MainFragmentDirections.mainToDetail()
            action.characterId = character.id
            navController?.navigate(action)
        }

        if (true) {
//            isFirstPage = false

            binding.detailsCharacterSeriesList.apply {
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

//                refreshList()

                return true
            }
        }

        return false
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
            setTitle(it?.name!!)
            binding.detailsCharacterDescription.text = it.description
            binding.detailsCharacterImage.load(it.thumbnail.getUrl())
        }

        viewModel?.comics?.observe(viewLifecycleOwner) {
            if (it?.size!! > 0) {
                binding.detailsCharacterComics.visible()
                setComics(it)
            }
        }

        viewModel?.series?.observe(viewLifecycleOwner) {
            if (it?.size!! > 0) {
                binding.detailsCharacterSeries.visible()
                setSeries(it)
            }
        }

        viewModel?.error?.observe(viewLifecycleOwner) {

        }

        viewModel?.errorComics?.observe(viewLifecycleOwner) {

        }

        viewModel?.errorSeries?.observe(viewLifecycleOwner) {

        }
    }

    private fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(
        StandardCharsets.UTF_8
    ))

    private fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }
}
