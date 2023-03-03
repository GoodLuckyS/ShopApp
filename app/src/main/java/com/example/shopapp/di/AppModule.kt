package com.example.shopapp.di

import com.example.shopapp.di.modules.LocalModule
import com.example.shopapp.di.modules.ViewModelModule
import dagger.Module

@Module(
    includes = [
        LocalModule::class,
        ViewModelModule::class
    ]
)
interface AppModule