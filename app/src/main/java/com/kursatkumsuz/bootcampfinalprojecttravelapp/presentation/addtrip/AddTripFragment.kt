package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addtrip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentAddTripBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class AddTripFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment() {

    private lateinit var binding: FragmentAddTripBinding
    private val viewModel by viewModels<AddTripViewModel>()
    private var title = ""
    private var country = ""
    private var city = ""
    private var startDate = ""
    private var endDate = ""
    private var totalDay: Long = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTripBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Call functions
        navigateSelectImageFragment()
        observeImageUrlLiveData()
        observeTripCreateStatus()
        getArgument()
        selectDate()
        createTrip()
        navigateBack()
    }

    /**
     * Observes Img Url Livedata
     * Passes url to [loadImage] function
     */
    private fun observeImageUrlLiveData() {
        viewModel.selectedImageUrl.observe(viewLifecycleOwner) {
            it?.let {
                loadImage(it)
            }
        }
    }

    /**
     * Saves data Into Room database
     * Runs [getTextValues] function to get current values
     */
    private fun createTrip() {
        binding.saveButton.setOnClickListener {
            getTextValues()
            viewModel.createTrip(title, country, city,  startDate , endDate , totalDay.toInt())
        }
    }

    /**
     * Observes livedata
     * Shows toast message by statuses
     * If status is success, runs [navigateTripFragment] function
     * If status is success, resets status message
     */
    private fun observeTripCreateStatus() {
        viewModel.createTripStatusMessage.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                    viewModel.resetStatusMessage()
                    navigateTripFragment()
                }
                Status.LOADING -> {}
                Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * Creates [MaterialDatePicker]
     * Sets value of [startDate], [endDate] and [totalDay]
     */
    private fun selectDate() {
        // Show DatePicker
        binding.apply {
            selectDateButton.setOnClickListener {
                val dateRangePicker =
                    MaterialDatePicker.Builder.dateRangePicker()
                        .setTitleText("Select dates")
                        .setSelection(
                            androidx.core.util.Pair(
                                MaterialDatePicker.thisMonthInUtcMilliseconds(),
                                MaterialDatePicker.todayInUtcMilliseconds()
                            )
                        ).build()
                dateRangePicker.show(parentFragmentManager, "date")

                // Get selected date range
                dateRangePicker.addOnPositiveButtonClickListener {
                    it?.let { date ->
                        val format = "dd MMM"
                        val dateFormatter = SimpleDateFormat(format)
                        startDate = dateFormatter.format(date.first)
                        endDate = dateFormatter.format(date.second)
                        dateText.text = "$startDate - $endDate"
                        totalDay = TimeUnit.MILLISECONDS.toDays( date.second - date.first);
                    }
                }
            }
        }
    }

    /**
     * Loads Image by given url
     * @param [url] for load Image
     */
    private fun loadImage(url : String) {
        glide.load(url).into(binding.selectImageView)

    }

    /**
     * Gets arguments that passed
     */
    private fun getArgument() {
        arguments?.getString("url")?.let {
            viewModel.setSelectedImageUrl(it)
        }
    }

    /**
     * Navigates to TripFragment
     */
    private fun navigateTripFragment() {
        findNavController().navigate(R.id.action_addTripFragment_to_trip)

    }

    /**
     * Navigates to SelectImageFragment
     */
    private fun navigateSelectImageFragment() {
        binding.selectImageView.setOnClickListener {
            findNavController().navigate(R.id.action_addTripFragment_to_searchImageFragment)
        }
    }

    private fun getTextValues() {
        binding.apply {
            title = titleText.text.toString()
            country = countryText.text.toString()
            city = cityText.text.toString()
        }
    }

    /**
     * Navigates to Back
     */
    private fun navigateBack() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}