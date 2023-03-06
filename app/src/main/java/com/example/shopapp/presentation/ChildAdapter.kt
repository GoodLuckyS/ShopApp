package com.example.shopapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.shopapp.databinding.ItemBinding
import com.example.shopapp.databinding.ItemFlashSaleBinding
import com.example.shopapp.domain.shop.Product
import com.example.shopapp.presentation.base.BaseAdapter
import com.example.shopapp.presentation.entity.ProductUIModel

class ChildAdapter : BaseAdapter<ProductUIModel, ItemBinding>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ProductUIModel> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            1 -> {
                ProductViewHolder.FlashSale(ItemFlashSaleBinding.inflate(inflater, parent, false))
            }
            2 -> {
                ProductViewHolder.Latest(ItemBinding.inflate(inflater, parent, false))
            }
            else -> {TODO()}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).discount == Product.UNDEF_FIELD.toString()) 2
        else 1
    }



    sealed class ProductViewHolder(binding: ViewBinding) : BaseViewHolder<ProductUIModel>(binding) {

        class Latest(private val binding: ItemBinding) : ProductViewHolder(binding) {
            override fun bind(item: ProductUIModel) {
                binding.apply {
                    name.text = item.name
                    price.text = item.price.toString()
                    textView3.text = item.category
                    Glide.with(imvBackground)
                        .load(item.imageUrl)
                        .into(imvBackground)
                }

            }
        }


        class FlashSale(private val binding: ItemFlashSaleBinding) : ProductViewHolder(binding) {
            override fun bind(item: ProductUIModel) {
                binding.apply {
                    tvName.text = item.name
                    tvCategory.text = item.category
                    tvPrice.text = item.price
                    tvSale.text = String.format("%s off", item.discount)
                    Glide.with(imvBackground)
                        .load(item.imageUrl)
                        .into(imvBackground)
                }
            }

        }

    }


}