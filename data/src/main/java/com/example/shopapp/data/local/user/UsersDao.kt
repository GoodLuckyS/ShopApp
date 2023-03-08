package com.example.shopapp.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shopapp.data.local.user.models.UserDbEntity
import com.example.shopapp.data.local.user.models.UserSignInTuple
import com.example.shopapp.data.local.user.models.UserUpdateImageTuple


@Dao
interface UsersDao {

    @Query("SELECT id FROM accounts WHERE email=:email")
    suspend fun signIn(email:String) : UserSignInTuple?

    @Query("SELECT id FROM accounts WHERE email=:email")
    suspend fun findUserByEmail(email: String) : UserSignInTuple?

    @Query("SELECT * FROM accounts WHERE id=:id")
    suspend fun getUserById(id:Long) : UserDbEntity

    @Update(entity = UserDbEntity::class)
    suspend fun updateUserImage(userUpdateImageTuple: UserUpdateImageTuple)

    @Insert
    suspend fun signUp(user: UserDbEntity)

}