package com.rafaelfelipeac.kotlinrepositories.features.repositories.presentation

import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.rafaelfelipeac.kotlinrepositories.R
import com.rafaelfelipeac.kotlinrepositories.core.plataform.base.BaseAdapter
import com.rafaelfelipeac.kotlinrepositories.databinding.ListItemRepositoryBinding
import com.rafaelfelipeac.kotlinrepositories.features.repositories.domain.model.Repository

class RepositoryAdapter : BaseAdapter<Repository>() {

    var clickListener: (repo: Repository) -> Unit = { }

    override fun getLayoutRes(): Int = R.layout.list_item_repository

    override fun View.bindView(item: Repository, viewHolder: ViewHolder) {
        val binding = ListItemRepositoryBinding.bind(this)

        setOnClickListener { clickListener(item) }

        binding.repositoryTitle.text = item.name
        binding.repositoryStarsMessage.text = String.format(
            context.getString(R.string.repo_stars_message), item.stars.toString()
        )
        binding.repositoryForksMessage.text = String.format(
            context.getString(R.string.repo_forks_message), item.forks.toString()
        )
        binding.repositoryOwnerName.text = item.owner.name
        binding.repositoryOwnerImage.load(item.owner.avatarUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setIsRecyclable(false)
    }
}
