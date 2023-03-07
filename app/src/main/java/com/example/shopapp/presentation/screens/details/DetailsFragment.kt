package com.example.shopapp.presentation.screens.details

import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentDetailsBinding
import com.example.shopapp.presentation.adapters.SelectorAdapter
import com.example.shopapp.presentation.base.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(
    FragmentDetailsBinding::inflate
) {

    private val viewModel: DetailsViewModel by viewModels { appComponent.viewModelFactory() }
    private val imagesAdapter = SelectorAdapter()
    private val colorsAdapter = SelectorAdapter()


    override fun initialize() {
        initRecyclerView()
    }

    override fun setupSubscribes() {
        viewModel.uiState.observe { uiState ->
            uiState.onSuccess {
                binding.apply {
                    tvName.text = it.name
                    tvDescription.text = it.description
                    tvPrice.text = it.price
                }
                imagesAdapter.submitList(it.imageUrls)
                colorsAdapter.submitList(it.colors)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView2.adapter = imagesAdapter

        val a = listOf<String>()
        Glide.with(binding.imvMainPicture)
            .load(a[0])
            .into(binding.imageView)
            .onLoadFailed(R.drawable.circle_shape.toDrawable())
    }

}