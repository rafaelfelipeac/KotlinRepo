package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import android.view.View
import coil.load
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseAdapter
import com.rafaelfelipeac.marvelapp.databinding.ListItemFavoriteBinding
import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite

class FavoriteAdapter : BaseAdapter<Favorite>() {

    var clickListener: (repo: Long) -> Unit = { }

    override fun getLayoutRes(): Int = R.layout.list_item_favorite

    override fun View.bindView(item: Favorite, viewHolder: ViewHolder) {
        val binding = ListItemFavoriteBinding.bind(this)

        binding.favoriteName.text = item.favoriteName
        binding.favoriteImage.load(item.favoriteUrl)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setIsRecyclable(false)
    }
}
