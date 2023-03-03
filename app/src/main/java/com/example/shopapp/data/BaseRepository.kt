package com.example.shopapp.data

import android.database.sqlite.SQLiteException
import com.example.shopapp.domain.AppException
import com.example.shopapp.domain.LocalResponse
import java.sql.SQLException

abstract class BaseRepository {

    suspend fun <T> doRequest(request: suspend () -> T): LocalResponse<T> {
        return try {
            LocalResponse.Success(value = request())
        } catch (exception: DataException) {
            LocalResponse.Error(value = AppException.Api(exception.message))
        } catch(exception:Exception) {
            LocalResponse.Error(value = AppException.Unexpected(exception.message.toString()))
        }
    }

}