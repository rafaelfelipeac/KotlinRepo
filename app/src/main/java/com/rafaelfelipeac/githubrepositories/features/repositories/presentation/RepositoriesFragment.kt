package com.rafaelfelipeac.githubrepositories.features.repositories.presentation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfelipeac.githubrepositories.R
import com.rafaelfelipeac.githubrepositories.core.extension.gone
import com.rafaelfelipeac.githubrepositories.core.extension.viewBinding
import com.rafaelfelipeac.githubrepositories.core.extension.visible
import com.rafaelfelipeac.githubrepositories.core.plataform.Config.LANGUAGE
import com.rafaelfelipeac.githubrepositories.core.plataform.Config.SORT
import com.rafaelfelipeac.githubrepositories.core.plataform.base.BaseFragment
import com.rafaelfelipeac.githubrepositories.databinding.FragmentRepositoriesBinding
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository
import kotlinx.coroutines.launch

var CURRENT_PAGE = 1

class RepositoriesFragment : BaseFragment() {

    private var binding by viewBinding<FragmentRepositoriesBinding>()

    var viewModel: RepositoriesViewModel? = null

    private var repositoryAdapter = RepositoryAdapter()

    private var isFirstPage = true
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        return FragmentRepositoriesBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel == null) {
            viewModel = viewModelProvider.repositoriesViewModel()
        }

        setLayout()

        viewModel?.getRepositories(LANGUAGE, SORT, CURRENT_PAGE)

        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_refresh, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuRefresh -> {
                refreshRepositories()

                return true
            }
        }

        return false
    }

    private fun setLayout() {
        binding.repositoriesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    isLoading = true

                    // load the next page
                    viewModel?.getRepositories(LANGUAGE, SORT, CURRENT_PAGE)

                    binding.repositoriesListLoader.visible()
                    binding.repositoriesList.scrollToPosition(repositoryAdapter.itemCount - 1)
                }
            }
        })
    }

    private fun observeViewModel() {
        viewModel?.repositories?.observe(viewLifecycleOwner) {
            isLoading = false

            binding.repositoriesList.visible()

            setList(it)

            CURRENT_PAGE += 1

            binding.repositoriesPlaceholder.gone()
            binding.repositoriesListLoader.gone()
            binding.repositoriesProgressBar.gone()
        }

        viewModel?.error?.observe(viewLifecycleOwner) {
            isLoading = false

            if ((!binding.repositoriesList.isVisible || binding.repositoriesProgressBar.isVisible)) {
                binding.repositoriesPlaceholder.visible()
            }

            binding.repositoriesListLoader.gone()
            binding.repositoriesProgressBar.gone()
        }
    }

    private fun setList(repositories: List<Repository>?) {
        repositoryAdapter.setItems(repositories)
        repositoryAdapter.clickListener = { repository ->
            Toast.makeText(context, repository.name, Toast.LENGTH_SHORT).show()
        }

        if (isFirstPage) {
            isFirstPage = false

            binding.repositoriesList.apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = repositoryAdapter
            }
        }
    }

    private fun refreshRepositories() {
        repositoryAdapter.clearItems()
        CURRENT_PAGE = 1

        binding.repositoriesList.gone()
        binding.repositoriesPlaceholder.gone()
        binding.repositoriesListLoader.gone()
        binding.repositoriesProgressBar.visible()


        viewModel?.getRepositories(LANGUAGE, SORT, CURRENT_PAGE)
    }
}
