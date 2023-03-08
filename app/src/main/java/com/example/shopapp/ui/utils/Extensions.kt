package com.example.shopapp.ui.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.request.RequestOptions
import com.example.shopapp.R

fun Fragment.findTopNavController(): NavController {
    val topLevelHost =
        requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

val glideOptions = RequestOptions()
    .centerCrop()
    .error(R.drawable.ic_no_image)