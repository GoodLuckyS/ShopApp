package com.example.shopapp.di.modules

import com.example.shopapp.data.remote.ShopRepositoryImpl
import com.example.shopapp.data.remote.service.RemoteService
import com.example.shopapp.di.utils.ApplicationScope
import com.example.shopapp.domain.shop.ShopRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface RemoteModule {
    @ApplicationScope
    @Binds
    fun bindShopRepository(impl: ShopRepositoryImpl) : ShopRepository
    companion object {
        @ApplicationScope
        @Provides
        fun provideService() =  RemoteService().configureRetrofit()
    }
}