package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen

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
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentGuideBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.GuideCategoryModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter.GuideCategoryRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter.MightNeedRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter.TopPickRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GuideFragment @Inject constructor(
    private val mightNeedAdapter: MightNeedRecyclerViewAdapter,
    private val topPickAdapter: TopPickRecyclerViewAdapter
) : Fragment() {

    private lateinit var binding: FragmentGuideBinding
    private val viewModel by viewModels<GuideViewModel>()
    private var mightNeedList = ArrayList<TravelModel>()
    private var topPickList = ArrayList<TravelModel>()
    private  var categoryAdapter = GuideCategoryRecyclerViewAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuideBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call functions
        observeMightNeedList()
        observeTopPickList()
        observeCategoryList()
        setVariables()
        navigateToSearchResultFragment()
        addBookmark()

    }

    /**
     * If text is not empty ,navigates to SearchResultFragment
     * Passes text value to SearchResultFragment
     */
    private fun navigateToSearchResultFragment() {
        binding.apply {
            searchTextField.setEndIconOnClickListener {

                if (searchText.text!!.isEmpty()) {
                    Toast.makeText(context, "Search should not be blank", Toast.LENGTH_SHORT).show()
                } else {
                    val action =
                        GuideFragmentDirections.actionGuideToSearchDetailFragment(searchText.text.toString())
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
            setVariable(BR.adapterTopPick, topPickAdapter)
            setVariable(BR.adapterMightNeed, mightNeedAdapter)
        }
    }

    /**
     * Observes mightNeedList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeMightNeedList() {
        viewModel.mightNeedList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    mightNeedList = it.data as ArrayList<TravelModel>
                    mightNeedAdapter.dataList = mightNeedList
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
     * Observes topPickList
     * If status is successful, runs [stopLoadingAnimation] function
     * If status is error, runs [showToast] and [stopLoadingAnimation]
     * If status is loading, shows loading animation
     */
    private fun observeTopPickList() {
        viewModel.topPickList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    topPickList = it.data as ArrayList<TravelModel>
                    topPickAdapter.dataList = topPickList
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
     * Observes categoryList
     * Sets RecyclerView adapter
     */
    private fun observeCategoryList() {
        viewModel.categoryList.observe(viewLifecycleOwner) {
            it?.let {
                categoryAdapter = GuideCategoryRecyclerViewAdapter(it as ArrayList)
                binding.categoryRecyclerView.adapter = categoryAdapter
            }
        }
    }


    /**
     * Gets Id of clicked list Item
     * Makes isBookmark value of selected data by given Id to true
     */
    private fun addBookmark() {
        topPickAdapter.setOnItemClickListener {
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