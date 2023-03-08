package com.example.shopapp.ui.screens.login

import android.text.Editable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentSignInBinding
import com.example.shopapp.ui.base.BaseFragment
import com.example.shopapp.ui.utils.AppTextWatcher

class SignInFragment : BaseFragment<FragmentSignInBinding>(
    FragmentSignInBinding::inflate
) {

    private val viewModel: SignInViewModel by viewModels { appComponent.viewModelFactory() }

    private val watcher = object : AppTextWatcher() {
        override fun afterTextChanged(s: Editable?) = viewModel.clearError()
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

        viewModel.isEmptyField.observe {
            binding.boxEmail.error = it.fieldInputMessage
            binding.boxPassword.error = it.fieldInputMessage
        }
    }

    private fun onSignInClickEvent() {
        viewModel.signIn(binding.edtEmail.text.toString())
    }

    override fun onResume() {
        super.onResume()
        binding.edtEmail.addTextChangedListener(watcher)
    }
}