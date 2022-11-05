package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentHomeBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.home.adapter.HomeAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private var adapter = HomeAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Call functions
        observeAllDataList()
        observeListByCategory()
        initializeTabLayout()
        initializeButton()
    }

    /**
     * Sets value to dataList by clicked tab Item
     */
    private fun initializeTabLayout() {

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> viewModel.loadAllList()

                        1 -> viewModel.loadByCategories("flight")

                        2 -> viewModel.loadByCategories("hotel")

                        3 -> viewModel.loadByCategories("transportation")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }

    /**
     * Sets variable
     */
    private fun setRecyclerViewAdapter(adapter : HomeAdapter) {
        binding.homeRecyclerView.adapter = adapter
    }

    /**
     * Observes allDataList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is successful, filters and excludes data that category is nearby, topdestination , toppick or mightneed
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeAllDataList() {
        viewModel.allDataList.observe(viewLifecycleOwner) { data ->
            data?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        adapter = it.data?.let { it1 -> HomeAdapter(it1) }!!
                        setRecyclerViewAdapter(adapter)
                        stopLoadingAnimation()
                    }
                    Status.LOADING -> {
                        binding.lottieView.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        stopLoadingAnimation()
                        showToast(it.message)
                    }
                }
            }
        }
    }

    /**
     * Observes listByCategory
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeListByCategory() {
        viewModel.listByCategory.observe(viewLifecycleOwner) { data ->
            data?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        adapter = it.data?.let { it1 -> HomeAdapter(it1) }!!
                        setRecyclerViewAdapter(adapter)
                        stopLoadingAnimation()
                    }
                    Status.LOADING -> {
                        binding.lottieView.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        showToast(it.message)
                        stopLoadingAnimation()
                    }
                }
            }
        }
    }


    /**
     * Adds animation to buttons
     */
    private fun initializeButton() {
        val bounceAnim = AnimationUtils.loadAnimation(context, R.anim.bounce)
        binding.apply {
            flightButton.setOnClickListener { flightButton.startAnimation(bounceAnim) }
            hotelButton.setOnClickListener { hotelButton.startAnimation(bounceAnim) }
            carsButton.setOnClickListener { carsButton.startAnimation(bounceAnim) }
            taxiButton.setOnClickListener { taxiButton.startAnimation(bounceAnim) }
        }
    }

    /**
     * Makes lottie view to Invisible
     * Pauses animation
     */
    private fun stopLoadingAnimation() {
        binding.apply {
            lottieView.visibility = View.INVISIBLE
            lottieView.pauseAnimation()
        }
    }

    /**
     * Shows toast message
     * @param [message] for [Toast]
     */
    private fun showToast(message: String?) {
        Toast.makeText(context, message ?: "Error", Toast.LENGTH_SHORT).show()
    }

}