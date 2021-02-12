package com.rafaelfelipeac.marvelapp.features.details.presentation

import android.view.View
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseAdapter
import com.rafaelfelipeac.marvelapp.databinding.ListItemDetailsInfoBinding
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character

class DetailsInfoAdapter : BaseAdapter<Character>() {

    var clickListener: (repo: Character) -> Unit = { }

    override fun getLayoutRes(): Int = R.layout.list_item_details_info

    override fun View.bindView(item: Character, viewHolder: ViewHolder) {
        val binding = ListItemDetailsInfoBinding.bind(this)

        setOnClickListener { clickListener(item) }

//        binding.characterTitle.text = item.name
//        binding.characterStarsMessage.text = String.format(
//            context.getString(R.string.character_stars_message), item.stars.toString()
//        )
//        binding.characterForksMessage.text = String.format(
//            context.getString(R.string.character_forks_message), item.forks.toString()
//        )
//        binding.characterOwnerName.text = item.owner.name
//        binding.characterOwnerImage.load(item.owner.avatarUrl) {
//            crossfade(true)
//            transformations(CircleCropTransformation())
//        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setIsRecyclable(false)
    }
}
