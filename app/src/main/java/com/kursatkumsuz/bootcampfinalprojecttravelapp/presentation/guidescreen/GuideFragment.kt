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
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter.MightNeedRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter.TopPickRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GuideFragment @Inject constructor(
 private val mightNeedAdapter : MightNeedRecyclerViewAdapter,
 private val topPickAdapter : TopPickRecyclerViewAdapter
) : Fragment() {


    private lateinit var binding: FragmentGuideBinding
    private val viewModel by viewModels<GuideViewModel>()
    private var mightNeedList = ArrayList<TravelModel>()
    private var topPickList = ArrayList<TravelModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuideBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeMightNeedList()
        observeTopPickList()
        initRecyclerView()
        navigateToSearchResultFragment()

    }

    private fun navigateToSearchResultFragment() {
        binding.apply {
            searchTextField.setEndIconOnClickListener {

                if (searchText.text!!.isEmpty()) {
                    Toast.makeText(context, "Search should not be blank", Toast.LENGTH_SHORT).show()
                } else {
                    val action = GuideFragmentDirections.actionGuideToSearchDetailFragment(searchText.text.toString())
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            mightNeedRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL , false)
            topPickRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL , false)

            setVariable(BR.adapterTopPick, topPickAdapter)
            setVariable(BR.adapterMightNeed, mightNeedAdapter)

        }
    }

    private fun observeMightNeedList() {
        viewModel.mightNeedList.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.SUCCESS -> {
                   mightNeedList = it.data as ArrayList<TravelModel>
                    mightNeedAdapter.dataList = mightNeedList
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        }
    }



    private fun observeTopPickList() {
        viewModel.topPickList.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.SUCCESS -> {
                    topPickList = it.data as ArrayList<TravelModel>
                    topPickAdapter.dataList = topPickList
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        }
    }

}