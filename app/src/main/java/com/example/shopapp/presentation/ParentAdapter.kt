package com.example.shopapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shopapp.databinding.ItemAndCategoryBinding
import com.example.shopapp.presentation.base.BaseAdapter
import com.example.shopapp.presentation.entity.ProductAndCategoryUI

class ParentAdapter : BaseAdapter<ProductAndCategoryUI,ItemAndCategoryBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ProductAndCategoryUI> {
        val inflater = LayoutInflater.from(parent.context)
        return ProductAndCategoryViewHolder(
            ItemAndCategoryBinding.inflate(inflater,parent,false)
        )
    }

    class ProductAndCategoryViewHolder(val binding: ItemAndCategoryBinding) : BaseViewHolder<ProductAndCategoryUI>(binding) {

        private val childAdapter = ChildAdapter()

        override fun bind(item: ProductAndCategoryUI) {
            binding.tvCategoryTitle.text = item.title
            binding.recyclerView.adapter = childAdapter
            childAdapter.submitList(item.list)
        }
    }
}