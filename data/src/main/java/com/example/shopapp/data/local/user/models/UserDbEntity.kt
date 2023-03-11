package com.example.shopapp.data.local.user.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.shopapp.domain.user.models.SignUpData
import com.example.shopapp.domain.user.models.User

@Entity(
    tableName = "accounts",
    indices = [
        Index("email", unique = true),
        Index("first_name", unique = true)
    ]
)
data class UserDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "uri")
    val uri: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "email")
    val email: String,
) {
    fun mapToDomain() = User(
        firstName = firstName,
        lastName = lastName,
        email = email,
        uri = uri,
        id = id
    )
}

fun SignUpData.mapToData() = UserDbEntity(
    id = 0,
    uri = "",
    firstName = firstName,
    lastName = lastName,
    email = email
)

