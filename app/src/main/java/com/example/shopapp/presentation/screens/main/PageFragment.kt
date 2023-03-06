package com.example.shopapp.presentation.screens.main

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.shopapp.databinding.FragmentPageBinding
import com.example.shopapp.presentation.ParentAdapter
import com.example.shopapp.presentation.base.BaseFragment
import com.example.shopapp.presentation.entity.mapToUI
import com.example.shopapp.presentation.screens.SignUpViewModel

class PageFragment : BaseFragment<FragmentPageBinding>(
    FragmentPageBinding::inflate
) {

    private val viewModel: PageViewModel by viewModels { appComponent.viewModelFactory() }
    val adapter = ParentAdapter()
    override fun initialize() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

    override fun setupSubscribes() {
        viewModel.uiState.observe {
            it.onSuccess {
                adapter.submitList(it.map {
                    it.mapToUI()
                })
            }
            it.setupViewVisibility(binding.group,binding.progressBar2,false)
        }
    }

}