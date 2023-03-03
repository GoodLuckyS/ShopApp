package com.example.shopapp.domain

import android.database.sqlite.SQLiteConstraintException

sealed class AppException {

    abstract val message : String

    class Api(override val message: String) : AppException()
    class Unexpected(override val message: String) : AppException()
}






