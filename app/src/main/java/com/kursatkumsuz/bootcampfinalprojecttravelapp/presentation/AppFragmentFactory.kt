package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.detailscreen.DetailFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.GuideFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter.MightNeedRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter.TopPickRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.homescreen.HomeFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.homescreen.adapter.HomeRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresultscreen.SearchResultFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresultscreen.adapter.SearchResultRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.SearchFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.adapter.NearbyRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.adapter.TopDestinationRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.adapter.TripRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.TripFragment
import javax.inject.Inject

class AppFragmentFactory @Inject constructor(
    private val homeRecyclerViewAdapter: HomeRecyclerViewAdapter,
    private val nearbyRecyclerViewAdapter: NearbyRecyclerViewAdapter,
    private val topDestinationRecyclerViewAdapter: TopDestinationRecyclerViewAdapter,
    private val topPickRecyclerViewAdapter: TopPickRecyclerViewAdapter,
    private val mightNeedRecyclerViewAdapter: MightNeedRecyclerViewAdapter,
    private val tripRecyclerViewAdapter: TripRecyclerViewAdapter,
    private val searchResultRecyclerViewAdapter: SearchResultRecyclerViewAdapter,
    private val glide: RequestManager,
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            HomeFragment::class.java.name -> HomeFragment(homeRecyclerViewAdapter)

            SearchFragment::class.java.name -> SearchFragment(
                nearbyRecyclerViewAdapter,
                topDestinationRecyclerViewAdapter
            )

            DetailFragment::class.java.name -> DetailFragment(glide)

            GuideFragment::class.java.name -> GuideFragment(
                mightNeedRecyclerViewAdapter,
                topPickRecyclerViewAdapter
            )

            TripFragment::class.java.name -> TripFragment(tripRecyclerViewAdapter)

            SearchResultFragment::class.java.name -> SearchResultFragment(searchResultRecyclerViewAdapter)

            else -> super.instantiate(classLoader, className)
        }
    }
}
