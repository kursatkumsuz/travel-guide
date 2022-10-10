package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentTripBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.adapter.BookmarkRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.adapter.TripRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TripFragment @Inject constructor(
    private val bookmarkAdapter: BookmarkRecyclerViewAdapter,
    private val tripAdapter: TripRecyclerViewAdapter
) : Fragment() {

    private lateinit var binding: FragmentTripBinding
    private val viewModel by viewModels<TripViewModel>()
    private var bookmarkList = ArrayList<TravelModel>()
    private var tripList = ArrayList<TripEntity>()
    private var isTripSelected = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTripBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call functions
       viewModel.loadBookMarkList()
       viewModel.loadTripList()
        setAdapter()
        initTabLayout()
        navigateChooseBottomSheet()
        observeTripList()
        enableSwipe()
    }

    /**
     * Enables Swipe
     */
    private fun enableSwipe() {
        val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            /**
             * If isTripSelected is true and any of recyclerview Item swiped to left, deletes data from room database
             * If isTripSelected is false and any of recyclerview Item swiped to left, makes value of isBookmark to false
             */
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.layoutPosition
                when (isTripSelected) {
                    true -> {
                        deleteTrip(tripAdapter.dataList[position])
                        println("isTripSelected True")
                    }
                    false -> {
                        val id = bookmarkAdapter.dataList[position].id
                        deleteBookmark(id)
                        println("isTripSelected False")
                    }
                }
            }
        }
        // Attaches callBack Into RecyclerView
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.tripRecyclerView)
    }

    /**
     * Sets value to dataList by clicked tab Item
     */
    private fun initTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> {
                            observeTripList()
                            isTripSelected = true
                            setAdapter()
                        }
                        1 -> {
                            observeBookmarkList()
                            isTripSelected = false
                            setAdapter()
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    /**
     * Sets adapter to RecyclerView by value of isTripSelected
     */
    private fun setAdapter() {
        binding.apply {
            when (isTripSelected) {
                true -> tripRecyclerView.adapter = tripAdapter
                else -> tripRecyclerView.adapter = bookmarkAdapter
            }
        }
    }

    /**
     * Observes bookmarkList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeBookmarkList() {
        viewModel.bookmarkList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    bookmarkList = it.data as ArrayList<TravelModel>
                    bookmarkAdapter.dataList = bookmarkList
                    println("Book mark List ${bookmarkAdapter.dataList}")
                    stopLoadingAnimation()
                }
                Status.LOADING -> {
                    binding.apply {
                        lottieView.visibility = View.VISIBLE
                        lottieView.playAnimation()
                    }
                }
                Status.ERROR -> {
                    stopLoadingAnimation()
                    showToast(it.message)
                }
            }
        }
    }

    /**
     * Observes tripList
     */
    private fun observeTripList() {
        viewModel.tripList.observe(viewLifecycleOwner) {
            it?.let {
                tripList = it as ArrayList<TripEntity>
                tripAdapter.dataList = tripList
            }
        }
    }

    /**
     * Observes Status
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeUpdateStatus() {
        viewModel.updateStatus.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    stopLoadingAnimation()
                }
                Status.LOADING -> {
                    binding.apply {
                        lottieView.visibility = View.VISIBLE
                        lottieView.playAnimation()
                    }
                }
                Status.ERROR -> {
                    showToast(it.message)
                    stopLoadingAnimation()
                }
            }
        }
    }

    /**
     * Navigates ChooseBottomSheetFragment
     */
    private fun navigateChooseBottomSheet() {
        binding.addTripFab.setOnClickListener {
            findNavController().navigate(R.id.action_trip_to_chooseBottomSheet)
        }
    }

    /**
     * Makes isBookmark value of selected data by given Id to false
     * @param [id] for updateData function
     */
    private fun deleteBookmark(id: String) {
        viewModel.updateData(id, false)
        observeUpdateStatus()
    }

    /**
     * Deletes data from room database
     * @param [trip] for deleteTrip function
     */
    private fun deleteTrip(trip: TripEntity) {
        viewModel.deleteTrip(trip)
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