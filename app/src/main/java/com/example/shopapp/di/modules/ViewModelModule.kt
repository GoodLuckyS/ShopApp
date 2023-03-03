package com.example.shopapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.shopapp.di.utils.ViewModelKey
import com.example.shopapp.presentation.screens.SignUpViewModel
import com.example.shopapp.presentation.screens.page2.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun bindsSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun bindsSignInViewModel(viewModel: SignInViewModel): ViewModel
}