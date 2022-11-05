package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentSearchBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.search.adapter.NearbyAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.search.adapter.TopDestinationAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment @Inject constructor(
    private val nearbyAdapter: NearbyAdapter,
    private val topDestinationAdapter: TopDestinationAdapter
) : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call functions
        viewModel.loadData()
        observeNearbyList()
        observeTopDestinationList()
        navigateToSearchResultFragment()
        addBookmark()
    }

    /**
     * If searchText is not empty, navigates SearchResultFragment
     * Passes text value to SearchResultFragment
     */
    private fun navigateToSearchResultFragment() {
        binding.apply {
            searchTextField.setEndIconOnClickListener {
                if (searchText.text!!.isEmpty()) {
                    Toast.makeText(context, "Search should not be blank", Toast.LENGTH_SHORT).show()
                } else {
                    val action =
                        SearchFragmentDirections.actionSearchToSearchDetailFragment(searchText.text.toString())
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    private fun setVariables() {

    }

    /**
     * Observes nearbyList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeNearbyList() {
        viewModel.nearbyList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val data = it.data
                    if (data != null) {
                        nearbyAdapter.dataList = data
                    }
                    binding.nearbyRecyclerView.adapter = nearbyAdapter
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

    /**
     * Observes topDestinationList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeTopDestinationList() {
        viewModel.topDestinationList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val data = it.data
                    if (data != null) {
                        topDestinationAdapter.dataList = data
                    }
                    binding.topDestinationRecyclerView.adapter = topDestinationAdapter
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

    /**
     * Makes isBookmark value of selected data by given Id to true
     */
    private fun addBookmark() {
        nearbyAdapter.setOnItemClickListener {
            viewModel.addBookmark(it, true)
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




