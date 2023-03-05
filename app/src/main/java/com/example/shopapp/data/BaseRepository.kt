package com.example.shopapp.data

import com.example.shopapp.domain.AppError
import com.example.shopapp.domain.AppResponse
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T> doRequest(request: suspend () -> T): AppResponse<T> {
        return try {
            AppResponse.Success(value = request())
        } catch (exception: DataException) {
            AppResponse.Error(value = AppError.Api(exception.message))
        } catch(exception:Exception) {
            AppResponse.Error(value = AppError.Unexpected(exception.message.toString()))
        }
    }

    suspend fun <T> doNetworkRequest(request: suspend() -> Response<T>) : AppResponse<T> {
        return try {
            request().let {
                if(it.isSuccessful && it.body()!=null){
                    AppResponse.Success(it.body()!!)
                } else {
                    AppResponse.Error(AppError.Api(it.message()))
                }
            }
        } catch (e:Exception){
            AppResponse.Error(AppError.Unexpected(e.message.toString()))
        }

    }

}