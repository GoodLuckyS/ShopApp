package com.example.shopapp.data

import com.example.shopapp.data.utils.DataException
import com.example.shopapp.data.utils.DataMapper
import com.example.shopapp.data.utils.mapToDomain
import com.example.shopapp.domain.utils.AppError
import com.example.shopapp.domain.utils.AppResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> doRequest(request: suspend () -> T): AppResponse<T> {
        return try {
            AppResponse.Success(value = request())
        } catch (exception: DataException) {
            AppResponse.Error(value = AppError.Api(exception.message))
        } catch (exception: Exception) {
            AppResponse.Error(value = AppError.Unexpected(exception.message.toString()))
        }
    }

    suspend fun <T : DataMapper<T, S>, S> doNetworkRequest(request: suspend () -> Response<T>): AppResponse<S> {
        return try {
            request().let {
                if (it.isSuccessful && it.body() != null) {
                    AppResponse.Success(it.body()!!.mapToDomain())
                } else {
                    AppResponse.Error(AppError.Api(it.message()))
                }
            }
        } catch (e: DataException) {
            AppResponse.Error(AppError.Unexpected(e.message))
        } catch (e: HttpException) {
            AppResponse.Error(AppError.Unexpected(String.format("%d %s", e.code(), e.message())))
        } catch (e: IOException) {
            AppResponse.Error(AppError.Unexpected(e.message ?: "Occured Error"))
        }

    }

}