package com.rafaelfelipeac.marvelapp.features.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafaelfelipeac.marvelapp.core.extension.viewBinding
import com.rafaelfelipeac.marvelapp.core.plataform.Config
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseFragment
import com.rafaelfelipeac.marvelapp.databinding.FragmentDetailsBinding
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CURRENT_PAGE

class DetailsFragment : BaseFragment() {

    private var binding by viewBinding<FragmentDetailsBinding>()

    var viewModel: DetailsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailsBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.detailsViewModel()
        }

        setLayout()

        viewModel?.getDetails(Config.LANGUAGE, Config.SORT, CURRENT_PAGE)
        viewModel?.getDetailsComics(Config.LANGUAGE, Config.SORT, CURRENT_PAGE)
        viewModel?.getDetailsSeries(Config.LANGUAGE, Config.SORT, CURRENT_PAGE)

        observeViewModel()
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
