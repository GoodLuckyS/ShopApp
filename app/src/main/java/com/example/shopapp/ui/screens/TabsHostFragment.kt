package com.example.shopapp.ui.screens

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentTabsBinding
import com.example.shopapp.ui.base.BaseFragment

class TabsHostFragment : BaseFragment<FragmentTabsBinding>(
    FragmentTabsBinding::inflate
) {

    override fun initialize() {
        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

}
