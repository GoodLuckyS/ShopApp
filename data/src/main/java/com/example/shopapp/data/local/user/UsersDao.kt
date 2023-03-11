package com.example.shopapp.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shopapp.data.local.user.models.UserDbEntity
import com.example.shopapp.data.local.user.models.UserSignInTuple
import com.example.shopapp.data.local.user.models.UserUpdateImageTuple
import kotlinx.coroutines.flow.Flow


@Dao
interface UsersDao {

    @Insert
    suspend fun signUp(user: UserDbEntity)

    @Query("SELECT id FROM accounts WHERE email=:email")
    suspend fun findUserByEmail(email: String): UserSignInTuple?

    @Query("SELECT id FROM accounts WHERE first_name=:firstName")
    suspend fun findUserByFirstName(firstName: String): UserSignInTuple?

    @Query("SELECT * FROM accounts WHERE id=:id")
    fun getUserById(id: Long): Flow<UserDbEntity?>

    @Query("SELECT uri FROM accounts WHERE id=:id")
    fun getUserImage(id: Long): Flow<String>

    @Update(entity = UserDbEntity::class)
    suspend fun updateUserImage(userUpdateImageTuple: UserUpdateImageTuple)

}