package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresultscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentSearchResultBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresultscreen.adapter.SearchResultRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class SearchResultFragment @Inject constructor(
    private var adapter: SearchResultRecyclerViewAdapter
) : Fragment() {

    private lateinit var binding: FragmentSearchResultBinding
    private val viewModel by viewModels<SearchResultViewModel>()
    private var searchString = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call functions
        getArgument()
        observeSearchResultList()
        setVariable()
        navigateBack()
    }

    /**
     * Sets variable
     */
    private fun setVariable() {
        binding.apply {
            setVariable(BR.adapterSearchResult, adapter)
        }
    }

    /**
     * Gets argument
     */
    private fun getArgument() {
        arguments?.let {
            searchString = SearchResultFragmentArgs.fromBundle(it).search.lowercase(Locale.ROOT)
        }
    }

    /**
     * Observes allDataList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is successful, filters the list
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeSearchResultList() {
        viewModel.searchResultList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    val searchResultList = it.data as ArrayList<TravelModel>
                    adapter.dataList = searchResultList.filter { i ->
                        i.title.lowercase(Locale.ROOT).contains(searchString) ||
                        i.city.lowercase(Locale.ROOT).contains(searchString) ||
                        i.country.lowercase(Locale.ROOT).contains(searchString) ||
                        i.description.lowercase(Locale.ROOT).contains(searchString)
                    }
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