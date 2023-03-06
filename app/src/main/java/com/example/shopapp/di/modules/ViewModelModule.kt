package com.example.shopapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.shopapp.di.utils.ViewModelKey
import com.example.shopapp.presentation.screens.SignUpViewModel
import com.example.shopapp.presentation.screens.login.SignInViewModel
import com.example.shopapp.presentation.screens.main.PageViewModel
import com.example.shopapp.presentation.screens.personal.PersonalViewModel
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


    @Binds
    @IntoMap
    @ViewModelKey(PersonalViewModel::class)
    fun bindsPersonalViewModel(viewModel: PersonalViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(PageViewModel::class)
    fun bindsPageViewModel(viewModel: PageViewModel): ViewModel
}