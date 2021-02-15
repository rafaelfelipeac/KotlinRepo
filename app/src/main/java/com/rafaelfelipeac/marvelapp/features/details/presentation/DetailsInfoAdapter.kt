package com.rafaelfelipeac.marvelapp.features.details.presentation

import android.view.View
import coil.load
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.core.plataform.base.BaseAdapter
import com.rafaelfelipeac.marvelapp.databinding.ListItemDetailsInfoBinding
import com.rafaelfelipeac.marvelapp.features.details.domain.model.DetailInfo
import com.rafaelfelipeac.marvelapp.features.details.domain.model.Thumbnail

class DetailsInfoAdapter : BaseAdapter<DetailInfo>() {

    var clickListener: (detailInfo: DetailInfo) -> Unit = { }

    override fun getLayoutRes(): Int = R.layout.list_item_details_info

    override fun View.bindView(item: DetailInfo, viewHolder: ViewHolder) {
        val binding = ListItemDetailsInfoBinding.bind(this)

        setOnClickListener { clickListener(item) }

        binding.detailInfoName.text = item.title
        binding.detailInfoImage.load(item.thumbnail.getUrl(Thumbnail.ImageType.PORTRAIT))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setIsRecyclable(false)
    }
}
