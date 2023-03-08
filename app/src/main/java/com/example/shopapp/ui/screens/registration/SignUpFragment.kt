package com.example.shopapp.ui.screens.registration

import android.text.Editable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentSignUpBinding
import com.example.shopapp.ui.base.BaseFragment
import com.example.shopapp.ui.utils.AppTextWatcher

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
) {

    private val viewModel: SignUpViewModel by viewModels { appComponent.viewModelFactory() }

    private val watcher = object : AppTextWatcher() {
        override fun afterTextChanged(s: Editable?) = viewModel.clearError()
    }

    override fun setupListeners() = with(binding) {
        btnSignIn.setOnClickListener {
            onSignUpClickEvent()
        }
        btnLogIn.setOnClickListener {
            onLogInClickEvent()
        }
        signInWithGoogle.setOnClickListener {
            viewModel.signInWithGoogle()
        }
    }

    override fun setupSubscribes() {

        viewModel.uiState.observe { uiState ->
            uiState.setupViewVisibility(binding.groupSignUp, binding.progressBar, true)
            uiState.onSuccess { findNavController().navigate(R.id.action_first_to_tabsHostFragment) }
            uiState.onError { it.setupApiErrors(binding.boxEmail) }
        }

        viewModel.isEmptyField.observe {
            binding.boxEmail.error = it.fieldEmailMessage
            binding.boxFirstName.error = it.fieldInputMessage
            binding.boxLastName.error = it.fieldInputMessage
        }

    }

    private fun onSignUpClickEvent() = with(binding) {
        viewModel.signUp(
            firstName = edtFirstName.text.toString(),
            lastName = edtLastName.text.toString(),
            email = edtEmail.text.toString()
        )
    }

    private fun onLogInClickEvent() {
        findNavController().navigate(R.id.action_first_to_signInFragment)
    }

    override fun onResume() {
        binding.edtEmail.addTextChangedListener(watcher)
        binding.edtLastName.addTextChangedListener(watcher)
        binding.edtFirstName.addTextChangedListener(watcher)
        super.onResume()
    }
}

