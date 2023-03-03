package com.example.shopapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopapp.data.local.user.models.UserDbEntity
import com.example.shopapp.data.local.user.UsersDao

@Database(
    version = 1,
    entities = [
       UserDbEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao() : UsersDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "users"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}