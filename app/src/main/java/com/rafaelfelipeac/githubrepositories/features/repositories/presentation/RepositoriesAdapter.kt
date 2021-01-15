package com.rafaelfelipeac.githubrepositories.features.repositories.presentation

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.rafaelfelipeac.githubrepositories.R
import com.rafaelfelipeac.githubrepositories.core.plataform.base.BaseAdapter
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository

class RepositoriesAdapter : BaseAdapter<Repository>() {

    var clickListener: (repo: Repository) -> Unit = { }

    override fun getLayoutRes(): Int = R.layout.list_item_repo

    @SuppressLint("ClickableViewAccessibility")
    override fun View.bindView(item: Repository, viewHolder: ViewHolder) {
        setOnClickListener { clickListener(item) }

        val repoTitle = viewHolder.itemView.findViewById<TextView>(R.id.repo_title)
        val repoStarsMessage = viewHolder.itemView.findViewById<TextView>(R.id.repo_stars_message)
        val repoForksMessage = viewHolder.itemView.findViewById<TextView>(R.id.repo_forks_message)
        val repoAuthorImage = viewHolder.itemView.findViewById<ImageView>(R.id.repo_author_image)
        val repoAuthorName = viewHolder.itemView.findViewById<TextView>(R.id.repo_author_name)

        repoTitle.text = item.name
        repoStarsMessage.text = String.format(
            context.getString(R.string.repo_stars_message), item.stars.toString())
        repoForksMessage.text = String.format(
            context.getString(R.string.repo_forks_message), item.forks.toString())
        repoAuthorName.text = item.author.name
        repoAuthorImage.load(item.author.authorImage) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setIsRecyclable(false)
    }
}
