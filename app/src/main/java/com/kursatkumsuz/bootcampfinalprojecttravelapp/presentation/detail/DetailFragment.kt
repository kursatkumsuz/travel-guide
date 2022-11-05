package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentDetailBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.detail.adapter.DetailImagesAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.downloadUrl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var selectedData: Array<TravelModel>
    private val viewModel by viewModels<DetailViewModel>()
    private var imagesAdapter = DetailImagesAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call functions
        getArgument()
        setVariables()
        setImage()
        addBookmark()
        observeUpdateStatus()
        navigateImageDetaiLFragment()
        navigateBack()
    }

    /**
     * Sets variable
     */
    private fun setVariables() {
        binding.apply {
            val title = selectedData[0].title
            val location = "${selectedData[0].city} , ${selectedData[0].country}"
            val description = selectedData[0].description
            imagesAdapter = DetailImagesAdapter(selectedData[0].images!!)

            setVariable(BR.title, title)
            setVariable(BR.location, location)
            setVariable(BR.description, description)
            setVariable(BR.adapterImages, imagesAdapter)
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

    private fun setImage() {
        binding.detailImageView.downloadUrl(selectedData[0].images?.get(0)?.url)
    }

    /**
     * Adds to bookmark
     * Makes isBookmark value of selected data by given Id to true
     */
    private fun addBookmark() {
        binding.detailAddBookMarkButton.setOnClickListener {
            viewModel.updateData(selectedData[0].id, true)
        }
    }

    /**
     * Navigates to TripFragment
     */
    private fun navigateTripFragment() {
        findNavController().navigate(R.id.action_detail_to_trip)

    }

    /**
     * Gets arguments that passed
     */
    private fun getArgument() {
        arguments?.let {
            selectedData = DetailFragmentArgs.fromBundle(it).data
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

    private fun navigateImageDetaiLFragment() {
        binding.expandImageButton.setOnClickListener {
            val imageList = selectedData[0].images?.map { i -> i.url }

           val action = DetailFragmentDirections.actionDetailToImageDetailFragment(imageList!!.toTypedArray())
           findNavController().navigate(action)
        }
    }

    /**
     * Navigates back
     */
    private fun navigateBack() {
        binding.backImageButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}