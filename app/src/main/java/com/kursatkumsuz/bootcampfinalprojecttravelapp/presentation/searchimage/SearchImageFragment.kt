package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchimage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentSearchImageBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchimage.adapter.SearchImageAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchImageFragment @Inject constructor(
    private val imageAdapter: SearchImageAdapter
) : Fragment() {

    private lateinit var binding: FragmentSearchImageBinding
    private val viewModel by viewModels<SearchImageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Call functions
        searchImage()
        observeImageList()
        setVariable()
        navigateAddTripFragment()
        navigateBack()

    }

    /**
     * Searches Images with 200 ms delay
     */
    private fun searchImage() {
        var job: Job? = null
        binding.imageSearchText.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(200)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchImage(it.toString())
                    }
                }
            }
        }
    }

    /**
     * Observes imageList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeImageList() {
        viewModel.imageList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    imageAdapter.images = it.data?.hits?.map { image -> image.webFormatURL } ?: listOf()
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

    /**
     * Sets variable
     */
    private fun setVariable() {
        binding.setVariable(BR.adapterImage, imageAdapter)
    }

    /**
     * Makes LottieView to Invisible
     * Pauses animation
     */
    private fun stopLoadingAnimation() {
        binding.apply {
            lottieView.visibility = View.INVISIBLE
            lottieView.pauseAnimation()
        }
    }

    /**
     * If clicked any list Item, navigates to AddTripFragment
     * Passes url to AddTripFragment
     */
    private fun navigateAddTripFragment() {
        imageAdapter.setOnItemClickListener { url ->
            val bundle = bundleOf("url" to url)
            findNavController().navigate(R.id.action_searchImageFragment_to_addTripFragment, bundle)
        }
    }

    /**
     * Navigates back
     */
    private fun navigateBack() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
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