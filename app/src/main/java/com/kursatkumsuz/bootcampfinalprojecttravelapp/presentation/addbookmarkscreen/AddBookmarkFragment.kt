package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addbookmarkscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.BR
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentAddBookmarkBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addbookmarkscreen.adapter.AddBookmarkAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class AddBookmarkFragment @Inject constructor(
    private val adapter: AddBookmarkAdapter
) : BottomSheetDialogFragment() {


    private val viewModel by viewModels<AddBookmarkViewModel>()
    private var allDataList = ArrayList<TravelModel>()
    private lateinit var binding: FragmentAddBookmarkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBookmarkBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Call functions
        observeAllDataList()
        addBookmark()
        observeUpdateStatus()
        setVariables()
    }

    /**
     * Observes all data list
     * Filters and exclude data that category is hotel or transportation
     */
    private fun observeAllDataList() {
        viewModel.allDataList.observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        allDataList = it.data as ArrayList<TravelModel>
                        adapter.dataList = allDataList.filterNot { data ->
                            data.category == "hotel" || data.category == "transportation" }
                    }
                    Status.LOADING -> {}
                    Status.ERROR -> {
                        showToast(it.message)
                    }
                }
            }
        }
    }

    /**
     * Observes Status
     * If status is successful, runs [navigateTripFragment] [stopLoadingAnimation] and [showToast] functions
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeUpdateStatus() {
        viewModel.updateStatus.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    showToast("Successfully Added!")
                    stopLoadingAnimation()
                    navigateTripFragment()

                }
                Status.LOADING -> {
                    binding.apply {
                        lottieView.visibility = View.VISIBLE
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
     * Sets variable
     */
    private fun setVariables() {
        binding.setVariable(BR.adapterAddBookmark, adapter)
    }

    /**
     * Gets Id of clicked list Item
     * Makes isBookmark value of selected data by given Id to true
     */
    private fun addBookmark() {
        adapter.setOnItemClickListener {
            viewModel.updateData(it, true)
        }
    }

    /**
     * Navigates TripFragment
     */
    private fun navigateTripFragment() {
        findNavController().navigate(R.id.action_addBookmarkFragment_to_trip)
    }

    /**
     * Shows toast message
     * @param [message] for [Toast]
     */
    private fun showToast(message: String?) {
        Toast.makeText(context, message ?: "Error", Toast.LENGTH_SHORT).show()
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

}