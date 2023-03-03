package com.example.shopapp.di.modules

import android.app.Application
import com.example.shopapp.data.local.AppDatabase
import com.example.shopapp.data.local.settings.AppSettings
import com.example.shopapp.data.local.settings.SharedPreferencesAppSettings
import com.example.shopapp.data.local.user.UserRepositoryImpl
import com.example.shopapp.data.local.user.UsersDao
import com.example.shopapp.di.utils.ApplicationScope
import com.example.shopapp.domain.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface LocalModule {


    @ApplicationScope
    @Binds
    fun bindAppSettings(impl: SharedPreferencesAppSettings): AppSettings

    @ApplicationScope
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideUsersDao(application: Application): UsersDao {
            return AppDatabase.getDatabase(application).usersDao()
        }

        @ApplicationScope
        @Provides
        fun provideAppSettings(application: Application): SharedPreferencesAppSettings {
            return SharedPreferencesAppSettings(application)
        }

    }

}