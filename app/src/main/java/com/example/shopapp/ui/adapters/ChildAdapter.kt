package com.example.shopapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.ItemBinding
import com.example.shopapp.databinding.ItemFlashSaleBinding
import com.example.shopapp.domain.shop.models.Category
import com.example.shopapp.ui.base.BaseAdapter
import com.example.shopapp.ui.models.ProductUIModel

class ChildAdapter(private val category: String) : BaseAdapter<ProductUIModel, ItemBinding>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ProductUIModel> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_flash_sale -> ProductViewHolder.FlashSale(ItemFlashSaleBinding.inflate(inflater, parent, false))
            R.layout.item -> ProductViewHolder.Latest(ItemBinding.inflate(inflater, parent, false))
            else -> ProductViewHolder.Latest(ItemBinding.inflate(inflater, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (category) {
            Category.Latest.string -> R.layout.item
            Category.FlashSale.string -> R.layout.item_flash_sale
            Category.Brands.string -> R.layout.item
            else -> R.layout.item
        }
    }


    sealed class ProductViewHolder(binding: ViewBinding) : BaseViewHolder<ProductUIModel>(binding) {

        protected fun loadImage(imageView: ImageView, url: String) {
            Glide.with(imageView)
                .load(url)
                .into(imageView)
        }

        class Latest(private val binding: ItemBinding) : ProductViewHolder(binding) {
            override fun bind(item: ProductUIModel) {
                binding.apply {
                    name.text = item.name
                    price.text = String.format("%s $", item.price)
                    textView3.text = item.category
                    loadImage(imvBackground, item.imageUrl)
                }

            }
        }

        class FlashSale(private val binding: ItemFlashSaleBinding) : ProductViewHolder(binding) {
            override fun bind(item: ProductUIModel) {
                binding.apply {
                    tvName.text = item.name
                    tvCategory.text = item.category
                    tvPrice.text = String.format("%s $", item.price)
                    tvSale.text = String.format("%s off", item.discount)
                    root.setOnClickListener {
                        onClickListener?.invoke(item)
                    }
                    loadImage(imvBackground, item.imageUrl)
                }
            }

        }

    }

}