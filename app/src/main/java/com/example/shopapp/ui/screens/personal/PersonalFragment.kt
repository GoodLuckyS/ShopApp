package com.example.shopapp.ui.screens.personal

import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentPersonalBinding
import com.example.shopapp.ui.base.BaseFragment
import com.example.shopapp.ui.utils.findTopNavController
import com.example.shopapp.ui.utils.glideOptions
import com.example.shopapp.ui.utils.showToast

class PersonalFragment : BaseFragment<FragmentPersonalBinding>(
    FragmentPersonalBinding::inflate
) {

    private val viewModel: PersonalViewModel by viewModels { appComponent.viewModelFactory() }

    override fun setupListeners() = with(binding) {

        tvLogout.setOnClickListener {
            viewModel.signOut()
            findTopNavController().navigate(R.id.first)
        }

        val openLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            try {
                it?.let {
                    updateImage(it.toString())
                }
            } catch (e: Exception) {
                showToast(getString(R.string.error_cant_open_file))
            }
        }
        binding.btnChangePhoto.setOnClickListener {
            openLauncher.launch(arrayOf("image/*"))
        }
    }

    override fun setupSubscribes() {

        viewModel.uiState.observe { uiState ->
            uiState.onSuccess {
                binding.apply {
                    tvName.text = String.format("%s %s", it.firstName, it.lastName)
                    setImage(it.uri)
                }
            }
            uiState.onError {
                it.setupUnexpectedErrors(requireContext())
            }
        }
    }

    private fun setImage(uri:String){
        Glide.with(binding.imgProfile)
            .load(uri.toUri())
            .apply(glideOptions)
            .into(binding.imgProfile)
    }

    private fun updateImage(uri: String) {
        setImage(uri)
        viewModel.updateUserImage(uri)

    }


}