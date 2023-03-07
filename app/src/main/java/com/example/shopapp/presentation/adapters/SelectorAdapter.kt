package com.example.shopapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.SelectorColorBinding
import com.example.shopapp.databinding.SelectorImagesBinding
import com.example.shopapp.databinding.SelectorTagBinding
import com.example.shopapp.presentation.base.BaseAdapter
import com.example.shopapp.presentation.models.SelectorUI

class SelectorAdapter : BaseAdapter<SelectorUI,ViewBinding>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<SelectorUI> {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            R.layout.selector_tag -> SelectorViewHolder.Tag(SelectorTagBinding.inflate(inflater,parent,false))
            R.layout.selector_color -> SelectorViewHolder.Color(SelectorColorBinding.inflate(inflater,parent,false))
            R.layout.selector_images -> SelectorViewHolder.Image(SelectorImagesBinding.inflate(inflater,parent,false))
            else -> {SelectorViewHolder.Image(SelectorImagesBinding.inflate(inflater,parent,false))}
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is SelectorUI.Tag -> R.layout.selector_tag
            is SelectorUI.Color -> R.layout.selector_color
            is SelectorUI.Image -> R.layout.selector_images
        }
    }


    sealed  class SelectorViewHolder(binding: ViewBinding) : BaseViewHolder<SelectorUI>(binding){

        class Tag(val binding: SelectorTagBinding) : SelectorViewHolder(binding) {
            override fun bind(item: SelectorUI) {
                if(item is SelectorUI.Tag){
                    binding.shapeableImageView.setImageResource(item.imageId)
                    binding.tvName.text = item.title
                }
            }

        }

        class Color(val binding: SelectorColorBinding) : SelectorViewHolder(binding){
            override fun bind(item: SelectorUI) {
                if(item is SelectorUI.Color) {
                    binding.color.setBackgroundColor(item.colorHex.toColorInt())
                }
            }

        }

        class Image(val binding: SelectorImagesBinding) : SelectorViewHolder(binding){
            override fun bind(item: SelectorUI) {
                if(item is SelectorUI.Image) {
                    Glide.with(binding.image)
                        .load(item.url)
                        .into(binding.image)
                }
            }

        }


    }

}