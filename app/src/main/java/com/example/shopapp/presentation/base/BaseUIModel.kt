package com.example.shopapp.presentation.base

interface BaseUIModel<T> {
    val id: T
    override fun equals(other: Any?): Boolean
}