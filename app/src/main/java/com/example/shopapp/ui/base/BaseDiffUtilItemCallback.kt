package com.example.shopapp.ui.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilItemCallback<T : BaseUIModel<S>, S> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}