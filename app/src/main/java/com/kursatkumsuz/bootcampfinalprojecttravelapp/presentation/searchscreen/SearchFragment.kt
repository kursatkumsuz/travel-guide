package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentSearchBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.adapter.NearbyRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.adapter.TopDestinationRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment @Inject constructor(
    private val nearbyAdapter: NearbyRecyclerViewAdapter,
    private val topDestinationAdapter: TopDestinationRecyclerViewAdapter
) : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    private var nearbyList = ArrayList<TravelModel>()
    private var topDestinationList = ArrayList<TravelModel>()
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
        observeNearbyList()
        observeTopDestinationList()
        setVariables()
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

    /**
     * Sets variables
     */
    private fun setVariables() {
        binding.apply {
            setVariable(BR.adapterNearby, nearbyAdapter)
            setVariable(BR.adapterTopDestination, topDestinationAdapter)
        }
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
                    nearbyList = it.data as ArrayList<TravelModel>
                    nearbyAdapter.dataList = nearbyList
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
                    topDestinationList = it.data as ArrayList<TravelModel>
                    topDestinationAdapter.dataList = topDestinationList
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
            viewModel.updateData(it, true)
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



