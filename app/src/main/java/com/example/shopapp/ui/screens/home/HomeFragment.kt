package com.example.shopapp.ui.screens.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentPageBinding
import com.example.shopapp.ui.adapters.DropDownAdapter
import com.example.shopapp.ui.adapters.ParentAdapter
import com.example.shopapp.ui.adapters.SelectorAdapter
import com.example.shopapp.ui.base.BaseFragment
import com.example.shopapp.ui.utils.AppResource

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
        adapter.onChildClickListener= {
           findNavController().navigate(R.id.action_pageFragment_to_detailsFragment)
        }
        val selectorAdapter = SelectorAdapter()
        binding.selectorRC.adapter = selectorAdapter
        selectorAdapter.submitList(AppResource.tagList)

    }

    override fun setupSubscribes() {
        viewModel.uiState.observe {
            it.onSuccess {
                adapter.submitList(it)
                binding.edtSearch.setAdapter(DropDownAdapter(requireContext(), data = it))
            }
            it.setupViewVisibility(binding.group,binding.progressBar2,false)
        }
    }

}