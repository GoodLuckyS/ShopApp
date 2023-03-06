package com.example.shopapp.presentation.utils

import com.example.shopapp.data.DataMapper

interface UIMapper <T, S> {
    fun T.mapToUI(): S
}

fun <T : UIMapper<T, S>, S> T.mapToUI() = this.mapToUI()