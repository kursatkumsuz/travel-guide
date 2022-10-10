package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addtripscreen.AddTripFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addbookmarkscreen.AddBookmarkFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addbookmarkscreen.adapter.AddBookmarkAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.detailscreen.DetailFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.detailscreen.adapter.DetailImagesRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.GuideFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter.MightNeedRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter.TopPickRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.homescreen.HomeFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.homescreen.adapter.HomeRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.imagedetailscreen.ImageDetailFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.imagedetailscreen.adapter.ViewPagerAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchimagescreen.SearchImageFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchimagescreen.adapter.SearchImageRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresultscreen.SearchResultFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresultscreen.adapter.SearchResultRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.SearchFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.adapter.NearbyRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.adapter.TopDestinationRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.adapter.BookmarkRecyclerViewAdapter
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.TripFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.adapter.TripRecyclerViewAdapter
import javax.inject.Inject

class AppFragmentFactory @Inject constructor(
    private val homeRecyclerViewAdapter: HomeRecyclerViewAdapter,
    private val nearbyRecyclerViewAdapter: NearbyRecyclerViewAdapter,
    private val topDestinationRecyclerViewAdapter: TopDestinationRecyclerViewAdapter,
    private val topPickRecyclerViewAdapter: TopPickRecyclerViewAdapter,
    private val mightNeedRecyclerViewAdapter: MightNeedRecyclerViewAdapter,
    private val bookmarkRecyclerViewAdapter: BookmarkRecyclerViewAdapter,
    private val tripRecyclerViewAdapter: TripRecyclerViewAdapter,
    private val searchResultRecyclerViewAdapter: SearchResultRecyclerViewAdapter,
    private val searchImageRecyclerViewAdapter: SearchImageRecyclerViewAdapter,
    private val detailImagesRecyclerViewAdapter: DetailImagesRecyclerViewAdapter,
    private val bottomSheetRecyclerViewAdapter: AddBookmarkAdapter,
    private val viewPagerAdapter: ViewPagerAdapter,
    private val glide: RequestManager,
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            HomeFragment::class.java.name -> HomeFragment(homeRecyclerViewAdapter)

            SearchFragment::class.java.name -> SearchFragment(
                nearbyRecyclerViewAdapter,
                topDestinationRecyclerViewAdapter
            )

            DetailFragment::class.java.name -> DetailFragment(
                detailImagesRecyclerViewAdapter,
                glide
            )

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

            ImageDetailFragment::class.java.name -> ImageDetailFragment(viewPagerAdapter)

            else -> super.instantiate(classLoader, className)
        }
    }
}
