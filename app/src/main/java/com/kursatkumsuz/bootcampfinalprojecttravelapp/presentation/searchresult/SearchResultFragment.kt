package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentSearchResultBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresult.adapter.SearchResultRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

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
        viewModel.searchList(searchString)

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
     * Observes Search Result List
     */
    private fun observeSearchResultList() {
        viewModel.searchResultList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.dataList = it
            }
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

}

