package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentTripBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.adapter.TripRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.ResponseStatus
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TripFragment @Inject constructor(
    private val tripAdapter: TripRecyclerViewAdapter
) : Fragment() {

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val id = tripAdapter.dataList[layoutPosition].id
            updateData(id)
        }
    }


    private lateinit var binding: FragmentTripBinding
    private val viewModel by viewModels<TripViewModel>()
    private var bookmarkList = ArrayList<TravelModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTripBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadBookMarkList()
        initRecyclerView()
        initTabLayout()
    }

    private fun initTabLayout() {

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> {
                            tripAdapter.dataList = listOf()
                            binding.addTripFab.visibility = View.VISIBLE
                        }
                        1 -> {
                            observeBookmarkList()
                            tripAdapter.dataList = bookmarkList
                            binding.addTripFab.visibility = View.GONE
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
            setVariable(BR.adapterTrip, tripAdapter)
            ItemTouchHelper(swipeCallBack).attachToRecyclerView(tripRecyclerView)
        }
    }

    private fun observeBookmarkList() {
        viewModel.bookmarkList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    bookmarkList = it.data as ArrayList<TravelModel>
                    tripAdapter.dataList = bookmarkList
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
                }
            }
        }
    }

    private fun observeUpdateStatus() {
        viewModel.updateStatus.observe(viewLifecycleOwner) {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    showToast("Deleted!")
                    stopLoadingAnimation()
                }
                ResponseStatus.LOADING -> {
                    binding.apply {
                        println("Update Loading....")
                        lottieView.visibility = View.VISIBLE
                        lottieView.playAnimation()
                    }
                }
                ResponseStatus.ERROR -> {
                    showToast(it.message)
                    stopLoadingAnimation()
                }
            }
        }
    }

    private fun updateData(id: String) {
        viewModel.updateData(id, false)
        observeUpdateStatus()
    }

    private fun stopLoadingAnimation() {
        binding.apply {
            lottieView.visibility = View.INVISIBLE
            lottieView.pauseAnimation()
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(context, message ?: "Error", Toast.LENGTH_SHORT).show()
    }

}