package com.example.shopapp.di

import android.app.Application
import com.example.shopapp.di.utils.ApplicationScope
import com.example.shopapp.presentation.utils.ViewModelFactory
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
        ): AppComponent

    }

}