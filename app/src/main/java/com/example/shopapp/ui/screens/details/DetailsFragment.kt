package com.example.shopapp.ui.screens.details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.shopapp.databinding.FragmentDetailsBinding
import com.example.shopapp.ui.adapters.SelectorAdapter
import com.example.shopapp.ui.base.BaseFragment
import com.example.shopapp.ui.models.SelectorUI
import com.example.shopapp.ui.utils.glideOptions

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(
    FragmentDetailsBinding::inflate
) {

    private val viewModel: DetailsViewModel by viewModels { appComponent.viewModelFactory() }
    private val imagesAdapter = SelectorAdapter()
    private val colorsAdapter = SelectorAdapter()


    override fun initialize() {
        initRecyclerView()

    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun setupSubscribes() {
        viewModel.uiState.observe { uiState ->
            uiState.setupViewVisibility(binding.group, binding.progressBar)
            uiState.onSuccess {
                binding.apply {
                    tvName.text = it.name
                    tvDescription.text = it.description
                    tvPrice.text = it.price
                    tvReviews.text = it.numbersOfReviews
                    tvSubPrice.text = it.price
                    tvRating.text = it.rating
                }
                imagesAdapter.submitList(it.imageUrls)
                colorsAdapter.submitList(it.colors)
                setImage(it.imageUrls.firstOrNull()?.url)
            }
            uiState.onError { it.setupUnexpectedErrors(requireContext()) }
        }
    }

    private fun initRecyclerView() {
        binding.rcvImages.adapter = imagesAdapter
        imagesAdapter.onClickListener = {
            setImage((it as SelectorUI.Image).url)
        }
        binding.rcvColors.adapter = colorsAdapter
    }

    private fun setImage(url: String?) {
        Glide.with(this)
            .load(url)
            .apply(glideOptions)
            .into(binding.imvMainPicture)
    }

}