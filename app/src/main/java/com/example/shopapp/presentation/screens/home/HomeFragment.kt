package com.example.shopapp.presentation.screens.home

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentPageBinding
import com.example.shopapp.presentation.adapters.ParentAdapter
import com.example.shopapp.presentation.adapters.SelectorAdapter
import com.example.shopapp.presentation.base.BaseFragment
import com.example.shopapp.presentation.models.AppRes
import com.example.shopapp.presentation.models.mapToUI

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
        selectorAdapter.submitList(AppRes.tagList)

    }

    override fun setupSubscribes() {
        viewModel.uiState.observe {
            it.onSuccess {
                adapter.submitList(it.map {
                    it.mapToUI()
                })
                Log.e("TAG",it.map { it.mapToUI() }.toString())
            }
            it.setupViewVisibility(binding.group,binding.progressBar2,false)
        }
    }

}