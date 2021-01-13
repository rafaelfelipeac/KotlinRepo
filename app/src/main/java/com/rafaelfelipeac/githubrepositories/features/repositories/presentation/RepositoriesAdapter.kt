package com.rafaelfelipeac.githubrepositories.features.repositories.presentation

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import coil.load
import com.rafaelfelipeac.githubrepositories.R
import com.rafaelfelipeac.githubrepositories.core.plataform.base.BaseAdapter
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository
import de.hdodenhof.circleimageview.CircleImageView

class RepositoriesAdapter : BaseAdapter<Repository>() {

    var clickListener: (repo: Repository) -> Unit = { }

    override fun getLayoutRes(): Int = R.layout.list_item_repo

    @SuppressLint("ClickableViewAccessibility")
    override fun View.bindView(item: Repository, viewHolder: ViewHolder) {
        setOnClickListener { clickListener(item) }

        val repoTitle = viewHolder.itemView.findViewById<TextView>(R.id.repo_title)
        val repoStarsQuantity = viewHolder.itemView.findViewById<TextView>(R.id.repo_stars_quantity)
        val repoForksQuantity = viewHolder.itemView.findViewById<TextView>(R.id.repo_forks_quantity)
        val repoAuthorImage = viewHolder.itemView.findViewById<CircleImageView>(R.id.repo_author_image)
        val repoAuthorName = viewHolder.itemView.findViewById<TextView>(R.id.repo_author_name)

        repoTitle.text = item.name
        repoStarsQuantity.text = item.stars.toString() + " stars"
        repoForksQuantity.text = item.forks.toString() + " forks"
        repoAuthorImage.load(item.authorImage)
        repoAuthorName.text = item.authorName
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setIsRecyclable(false)
    }
}
