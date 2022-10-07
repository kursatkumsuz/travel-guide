package com.kursatkumsuz.bootcampfinalprojecttravelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ActivityMainBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.AppFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var fragmentFactory: AppFragmentFactory
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initNavHostFragment()
        hideStatusBar()
        navHostFragment()
        hideBottomNavigation()

    }

    private fun initNavHostFragment() {
        navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun navHostFragment() {
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

    }

    private fun hideStatusBar() {

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    }

    private fun hideBottomNavigation() {

        navController.addOnDestinationChangedListener { _, destination, _ ->

            if (destination.label == "fragment_detail" || destination.label == "fragment_search_detail") {
                binding.bottomNavigationView.visibility = View.INVISIBLE
            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

}