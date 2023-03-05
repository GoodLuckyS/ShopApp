package com.example.shopapp.presentation.screens.personal

import androidx.fragment.app.viewModels
import com.example.shopapp.databinding.FragmentPersonalBinding
import com.example.shopapp.presentation.base.BaseFragment

class PersonalFragment : BaseFragment<FragmentPersonalBinding>(
    FragmentPersonalBinding::inflate
) {

    private val viewModel: PersonalViewModel by viewModels { appComponent.viewModelFactory() }

    override fun setupListeners() = with(binding) {
        groupLogOut.setOnClickListener {
            viewModel.signOut()
        }
    }

    override fun setupSubscribes() {
        viewModel.uiState.observe { uiState ->
            uiState.onSuccess {

            }
            uiState.onError {
                it.setupUnexpectedErrors(requireContext())
            }
        }
    }


}