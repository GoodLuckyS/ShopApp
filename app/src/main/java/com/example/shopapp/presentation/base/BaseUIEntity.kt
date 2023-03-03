package com.example.shopapp.presentation.base

interface BaseUIEntity<T>  {
    val id: T
    override fun equals(other: Any?): Boolean
}