package com.example.shopapp.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shopapp.data.local.user.models.SignInTuple
import com.example.shopapp.data.local.user.models.UserDbEntity


@Dao
interface UsersDao {

    @Query("SELECT id FROM accounts WHERE email=:email")
    suspend fun signIn(email:String) : SignInTuple?

    @Query("SELECT id FROM accounts WHERE email=:email")
    suspend fun findUserByEmail(email: String) : SignInTuple?

    @Insert
    suspend fun signUp(user: UserDbEntity)

}