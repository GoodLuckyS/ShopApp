package com.example.shopapp.data.local.user.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.shopapp.domain.user.User

@Entity(
   tableName = "accounts",
    indices = [
        Index("email", unique = true)
    ]
)
data class UserDbEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Long,
    @ColumnInfo(name = "first_name")
    val firstName:String,
    @ColumnInfo(name = "last_name")
    val lastName:String,
    @ColumnInfo(name = "email")
    val email:String,

) {
    fun toDomain() = User(
        firstName, lastName, email,id
    )
}