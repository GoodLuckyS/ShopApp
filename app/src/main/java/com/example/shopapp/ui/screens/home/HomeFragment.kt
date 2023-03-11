package com.example.shopapp.ui.screens.home

import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentPageBinding
import com.example.shopapp.ui.adapters.DropDownAdapter
import com.example.shopapp.ui.adapters.ParentAdapter
import com.example.shopapp.ui.adapters.SelectorAdapter
import com.example.shopapp.ui.base.BaseFragment
import com.example.shopapp.ui.utils.AppResource
import com.example.shopapp.ui.utils.setImageURI

class HomeFragment : BaseFragment<FragmentPageBinding>(
    FragmentPageBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels { appComponent.viewModelFactory() }
    private val adapter = ParentAdapter()

    override fun initialize() {
        initRecyclerView()
    }

    private fun initRecyclerView() {

        binding.recyclerView.adapter = adapter
        adapter.onChildClickListener = {
            findNavController().navigate(R.id.action_pageFragment_to_detailsFragment)
        }
        val selectorAdapter = SelectorAdapter()
        binding.selectorRC.adapter = selectorAdapter
        selectorAdapter.submitList(AppResource.tagList)

    }

    override fun setupSubscribes() {

        viewModel.uiState.observe { uiState ->
            uiState.setupViewVisibility(binding.group, binding.progressBar, false)
            uiState.onSuccess {
                adapter.submitList(it)
                binding.edtSearch.setAdapter(DropDownAdapter(requireContext(), data = it))
            }
            uiState.onError { it.setupUnexpectedErrors(requireContext()) }
        }
        viewModel.userImageUri.observe {
            setImageURI(it.toUri(), binding.imgProfile)
        }
    }

}