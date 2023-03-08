package com.example.shopapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.shopapp.di.utils.ViewModelKey
import com.example.shopapp.presentation.screens.details.DetailsViewModel
import com.example.shopapp.presentation.screens.home.HomeViewModel
import com.example.shopapp.presentation.screens.login.SignInViewModel
import com.example.shopapp.presentation.screens.personal.PersonalViewModel
import com.example.shopapp.presentation.screens.registration.SignUpViewModel
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
    @ViewModelKey(HomeViewModel::class)
    fun bindsHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindsDetailsViewModel(viewModel: DetailsViewModel) : ViewModel
}