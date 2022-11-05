package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addtrip.AddTripFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addbookmark.AddBookmarkFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addbookmark.adapter.AddBookmarkAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.detail.DetailFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guide.GuideFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guide.adapter.MightNeedAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guide.adapter.TopPickAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.home.HomeFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.imagedetail.ImageDetailFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchimage.SearchImageFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchimage.adapter.SearchImageAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresult.SearchResultFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresult.adapter.SearchResultRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.search.SearchFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.search.adapter.NearbyAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.search.adapter.TopDestinationAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.trip.adapter.BookmarkRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.trip.TripFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.trip.adapter.TripRecyclerViewAdapter
import javax.inject.Inject

class AppFragmentFactory @Inject constructor(

    private val nearbyAdapter: NearbyAdapter,
    private val topDestinationAdapter: TopDestinationAdapter,
    private val topPickRecyclerViewAdapter: TopPickAdapter,
    private val mightNeedRecyclerViewAdapter: MightNeedAdapter,
    private val bookmarkRecyclerViewAdapter: BookmarkRecyclerViewAdapter,
    private val tripRecyclerViewAdapter: TripRecyclerViewAdapter,
    private val searchResultRecyclerViewAdapter: SearchResultRecyclerViewAdapter,
    private val searchImageRecyclerViewAdapter: SearchImageAdapter,
    private val bottomSheetRecyclerViewAdapter: AddBookmarkAdapter,
    private val glide: RequestManager,
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            HomeFragment::class.java.name -> HomeFragment()

            SearchFragment::class.java.name -> SearchFragment(nearbyAdapter, topDestinationAdapter)

            DetailFragment::class.java.name -> DetailFragment()

            GuideFragment::class.java.name -> GuideFragment(
                mightNeedRecyclerViewAdapter,
                topPickRecyclerViewAdapter
            )

            TripFragment::class.java.name -> TripFragment(
                bookmarkRecyclerViewAdapter,
                tripRecyclerViewAdapter
            )

            SearchResultFragment::class.java.name -> SearchResultFragment(
                searchResultRecyclerViewAdapter
            )

            SearchImageFragment::class.java.name -> SearchImageFragment(
                searchImageRecyclerViewAdapter
            )

            AddTripFragment::class.java.name -> AddTripFragment(glide)

            AddBookmarkFragment::class.java.name -> AddBookmarkFragment(
                bottomSheetRecyclerViewAdapter
            )

            ImageDetailFragment::class.java.name -> ImageDetailFragment()

            else -> super.instantiate(classLoader, className)
        }
    }
}
