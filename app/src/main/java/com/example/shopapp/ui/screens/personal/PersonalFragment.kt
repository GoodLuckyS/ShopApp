package com.example.shopapp.ui.screens.personal

import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.navOptions
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentPersonalBinding
import com.example.shopapp.ui.base.BaseFragment
import com.example.shopapp.ui.utils.findTopNavController
import com.example.shopapp.ui.utils.setImageURI
import com.example.shopapp.ui.utils.showToast

class PersonalFragment : BaseFragment<FragmentPersonalBinding>(
    FragmentPersonalBinding::inflate
) {

    private val viewModel: PersonalViewModel by viewModels { appComponent.viewModelFactory() }

    override fun setupListeners() = with(binding) {

        val openLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            try {
                it?.let { updateImage(it.toString()) }
            } catch (e: Exception) {
                showToast(getString(R.string.error_cant_open_file))
            }
        }

        tvLogout.setOnClickListener {
            viewModel.signOut()
        }

        binding.btnChangePhoto.setOnClickListener {
            openLauncher.launch(arrayOf("image/*"))
        }
    }

    override fun setupSubscribes() {

        viewModel.uiState.observe { uiState ->
            uiState.onError { it.setupUnexpectedErrors(requireContext()) }
            uiState.onSuccess {
                findTopNavController().navigate(
                    R.id.signUpFragment,
                    null,
                    navOptions {
                        popUpTo(R.id.tabsHostFragment) { inclusive = true }
                    })
            }
        }

        viewModel.user.observe {
            binding.apply {
                tvName.text = String.format("%s %s", it.firstName, it.lastName)
                setImageURI(it.uri.toUri(), binding.imgProfile)
            }
        }
    }

    private fun updateImage(uri: String) {
        viewModel.updateUserImage(uri)
    }


}