package com.example.shopapp.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.shopapp.MainApplication
import com.example.shopapp.R
import com.example.shopapp.data.SignInException
import com.example.shopapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val appComponent by lazy {
        (application as MainApplication).appComponent
    }

    private lateinit var navController: NavController

    private val viewModel: MainViewModel by viewModels { appComponent.viewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectRootNavController()
        prepareRootNavController()


    }



    private fun prepareRootNavController() {
        val graph = navController.navInflater.inflate(getMainNavGraphId())

        lifecycleScope.launchWhenStarted {
            viewModel.auth.collect {
                if (!it) graph.setStartDestination(getAuthDestination())
                else graph.setStartDestination(getTabsDestination())
                navController.graph = graph
            }
        }
    }

    private fun injectRootNavController() {
        val navHost = supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController
    }

    private fun getMainNavGraphId(): Int = R.navigation.nav_graph


    private fun getTabsDestination(): Int = R.id.tabsHostFragment


    private fun getAuthDestination(): Int = R.id.first
}