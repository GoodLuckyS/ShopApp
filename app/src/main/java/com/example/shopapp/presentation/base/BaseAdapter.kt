package com.example.shopapp.presentation.base


import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T : BaseUIModel<String>, VB : ViewBinding> :
    ListAdapter<T, BaseAdapter.BaseViewHolder<T>>(BaseDiffUtilItemCallback()) {

    var onClickListener: ((item: T) -> Unit)? = null
    var onChildClickListener: ((item: BaseUIModel<String>) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.onClickListener = onClickListener
        holder.onChildClickListener = onChildClickListener
        holder.bind(item)
    }

    abstract class BaseViewHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        var onClickListener: ((item: T) -> Unit)? = null
        var onChildClickListener: ((item: BaseUIModel<String>) -> Unit)? = null
        abstract fun bind(item: T)
    }
}


