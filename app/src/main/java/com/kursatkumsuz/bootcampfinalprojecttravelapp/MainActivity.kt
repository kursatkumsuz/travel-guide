package com.kursatkumsuz.bootcampfinalprojecttravelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navHostFragment()
    }

    private fun navHostFragment() {
        binding.apply {
            val navHostFragment  = supportFragmentManager.findFragmentById(fragmentContainerView.id) as NavHostFragment
            NavigationUI.setupWithNavController(bottomNavigationView,navHostFragment.navController)
        }
    }
}