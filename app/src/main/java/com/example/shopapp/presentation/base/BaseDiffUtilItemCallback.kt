package com.example.shopapp.presentation.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilItemCallback<T: BaseUIModel<S>,S> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}