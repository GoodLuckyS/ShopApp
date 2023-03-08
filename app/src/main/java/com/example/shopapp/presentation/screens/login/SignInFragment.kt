package com.example.shopapp.presentation.screens.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentSignInBinding
import com.example.shopapp.presentation.base.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding>(
    FragmentSignInBinding::inflate
) {

    private val viewModel: SignInViewModel by viewModels {
        appComponent.viewModelFactory()
    }


    override fun setupListeners() = with(binding) {
        btnSignIn.setOnClickListener {
            onSignInClickEvent()
        }
    }

    override fun setupSubscribes() {
        viewModel.uiState.observe { uiState ->
            uiState.setupViewVisibility(binding.group, binding.progressBar, true)
            uiState.onSuccess { findNavController().navigate(R.id.action_signInFragment_to_tabsHostFragment) }
            uiState.onError { it.setupApiErrors(binding.boxEmail, binding.boxPassword) }
        }
    }

    private fun onSignInClickEvent() {
        viewModel.signIn(binding.edtEmail.text.toString())
    }


}