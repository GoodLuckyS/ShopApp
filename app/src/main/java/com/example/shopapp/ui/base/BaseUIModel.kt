package com.example.shopapp.ui.base

interface BaseUIModel<T> {
    val id: T
    override fun equals(other: Any?): Boolean
}