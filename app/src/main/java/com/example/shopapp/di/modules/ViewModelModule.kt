package com.example.shopapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.shopapp.di.utils.ViewModelKey
import com.example.shopapp.ui.MainViewModel
import com.example.shopapp.ui.screens.details.DetailsViewModel
import com.example.shopapp.ui.screens.home.HomeViewModel
import com.example.shopapp.ui.screens.login.SignInViewModel
import com.example.shopapp.ui.screens.personal.PersonalViewModel
import com.example.shopapp.ui.screens.registration.SignUpViewModel
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
    fun bindsDetailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsTabsHostViewModel(viewModel: MainViewModel): ViewModel
}