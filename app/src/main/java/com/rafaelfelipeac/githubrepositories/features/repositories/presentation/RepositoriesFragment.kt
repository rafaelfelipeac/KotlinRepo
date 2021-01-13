package com.rafaelfelipeac.githubrepositories.features.repositories.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafaelfelipeac.githubrepositories.core.extension.viewBinding
import com.rafaelfelipeac.githubrepositories.core.plataform.base.BaseFragment
import com.rafaelfelipeac.githubrepositories.databinding.FragmentRepositoriesBinding

class RepositoriesFragment : BaseFragment() {

    private val viewModel by lazy { viewModelProvider.repositoriesViewModel() }

    private var binding by viewBinding<FragmentRepositoriesBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentRepositoriesBinding.inflate(inflater, container, false).run {
            binding = this
            binding.root
        }
    }
}
