package com.rafaelfelipeac.marvelapp.features.characters.presentation

import android.view.View
import coil.load
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseAdapter
import com.rafaelfelipeac.marvelapp.databinding.ListItemCharacterBinding
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character

class CharacterAdapter : BaseAdapter<Character>() {

    var clickListener: (repo: Long) -> Unit = { }

    override fun getLayoutRes(): Int = R.layout.list_item_character

    override fun View.bindView(item: Character, viewHolder: ViewHolder) {
        val binding = ListItemCharacterBinding.bind(this)

        setOnClickListener { clickListener(item.id) }

        binding.detailInfoName.text = item.name
        binding.detailInfoImage.load(item.thumbnail.getUrl())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setIsRecyclable(false)
    }
}
