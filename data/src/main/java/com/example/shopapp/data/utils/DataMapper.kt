package com.example.shopapp.data.utils

interface DataMapper<T, S> {
    fun T.mapToDomain(): S
}

fun <T : DataMapper<T, S>, S> T.mapToDomain() = this.mapToDomain()