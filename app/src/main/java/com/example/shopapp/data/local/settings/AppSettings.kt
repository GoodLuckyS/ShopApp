package com.example.shopapp.data.local.settings

interface AppSettings {

    fun getCurrentAccountId(): Long

    fun setCurrentAccountId(id: Long)

    companion object {
        const val UNDEFINED_ID = -1L
    }
}
