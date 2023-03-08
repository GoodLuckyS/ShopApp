package com.example.shopapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shopapp.databinding.ItemAndCategoryBinding
import com.example.shopapp.ui.base.BaseAdapter
import com.example.shopapp.ui.models.ProductsAndCategoryUI

class ParentAdapter : BaseAdapter<ProductsAndCategoryUI,ItemAndCategoryBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ProductsAndCategoryUI> {
        val inflater = LayoutInflater.from(parent.context)
        return ProductAndCategoryViewHolder(
            ItemAndCategoryBinding.inflate(inflater,parent,false)
        )
    }

    class ProductAndCategoryViewHolder(val binding: ItemAndCategoryBinding) : BaseViewHolder<ProductsAndCategoryUI>(binding) {

        override fun bind(item: ProductsAndCategoryUI) {
            val childAdapter = ChildAdapter(item.title)
            binding.tvCategoryTitle.text = item.title
            binding.recyclerView.adapter = childAdapter
            childAdapter.submitList(item.list)
            childAdapter.onClickListener = onChildClickListener
        }
    }
}