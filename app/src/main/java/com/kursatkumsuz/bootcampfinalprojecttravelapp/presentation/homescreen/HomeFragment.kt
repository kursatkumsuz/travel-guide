package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.homescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentHomeBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.homescreen.adapter.HomeRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor(
    private val homeRecyclerViewAdapter: HomeRecyclerViewAdapter
) : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private var listAllData = ArrayList<TravelModel>()
    private var flightList = ArrayList<TravelModel>()
    private var hotelList = ArrayList<TravelModel>()
    private var transportationList = ArrayList<TravelModel>()

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
        observeFlightList()
        observeHotelList()
        observeTransportationList()
        setVariable()
        initTabLayout()
        addAnimationToButtons()
    }

    /**
     * Sets value to dataList by clicked tab Item
     */
    private fun initTabLayout() {

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> {
                            homeRecyclerViewAdapter.dataList = listAllData
                        }

                        1 -> {
                            homeRecyclerViewAdapter.dataList = flightList
                        }

                        2 -> {
                            homeRecyclerViewAdapter.dataList = hotelList
                        }

                        3 -> {
                            homeRecyclerViewAdapter.dataList = transportationList
                        }
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
    private fun setVariable() {

        binding.setVariable(BR.homeAdapter, homeRecyclerViewAdapter)
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
                        listAllData = it.data?.filterNot { i ->
                            i.category == "nearby" ||
                                    i.category == "topdestination" ||
                                    i.category == "toppick" ||
                                    i.category == "mightneed"
                        } as ArrayList

                        stopLoadingAnimation()
                        homeRecyclerViewAdapter.dataList = listAllData
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
     * Observes flightList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeFlightList() {
        viewModel.flightList.observe(viewLifecycleOwner)  { data ->
            data?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        flightList = it.data as ArrayList
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
     * Observes hotelList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeHotelList() {
        viewModel.hotelList.observe(viewLifecycleOwner) { data ->
            data?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        hotelList = it.data as ArrayList
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
     * Observes transportationList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeTransportationList() {
        viewModel.transportationList.observe(viewLifecycleOwner) { data ->
            data?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        transportationList = it.data as ArrayList
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
    private fun addAnimationToButtons() {
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