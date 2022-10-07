package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.homescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
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



        observeAllList()
        observeFlightList()
        observeHotelList()
        observeTransportationList()
        initRecyclerView()
        initTabLayout()


    }

    private fun initTabLayout() {

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> {
                            homeRecyclerViewAdapter.dataList = listAllData.take(10)
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

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun initRecyclerView() {

        binding.apply {
            homeRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setVariable(BR.homeAdapter, homeRecyclerViewAdapter)
        }
    }

    private fun observeAllList() {
        viewModel.allDataList.observe(viewLifecycleOwner) { data ->
            data?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        listAllData = it.data as ArrayList<TravelModel>
                        homeRecyclerViewAdapter.dataList = listAllData.take(10)
                    }
                    Status.LOADING -> {
                        Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message ?: "Error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun observeFlightList() {
        viewModel.flightList.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        flightList = it.data as ArrayList<TravelModel>
                    }
                    Status.LOADING -> {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message ?: "Error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun observeHotelList() {
        viewModel.hotelList.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        hotelList = it.data as ArrayList<TravelModel>
                    }
                    Status.LOADING -> {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message ?: "Error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun observeTransportationList() {
        viewModel.transportationList.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        transportationList = it.data as ArrayList<TravelModel>
                        stopLoadingAnimation()
                    }
                    Status.LOADING -> {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                        binding.lottieView.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message ?: "Error", Toast.LENGTH_LONG).show()
                        stopLoadingAnimation()
                    }
                }
            }
        })
    }

    private fun stopLoadingAnimation() {
        binding.apply {
            lottieView.visibility = View.INVISIBLE
            lottieView.pauseAnimation()
        }
    }

}