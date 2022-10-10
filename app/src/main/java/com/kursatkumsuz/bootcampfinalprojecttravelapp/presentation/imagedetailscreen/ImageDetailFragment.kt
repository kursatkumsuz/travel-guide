package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.imagedetailscreen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.BR
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentImageDetailBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.imagedetailscreen.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ImageDetailFragment @Inject constructor(
    private val adapter: ViewPagerAdapter
): DialogFragment() {


    private lateinit var binding : FragmentImageDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgument()
        setVariables()

    }

    /**
     * Gets arguments
     */
    private fun getArgument() {
        arguments?.let {
            val images = ImageDetailFragmentArgs.fromBundle(it).images
            adapter.imageList = images.toList()
        }
    }

    /**
     * Sets variable
     */
    private fun setVariables() {
        binding.apply {
            setVariable(BR.adapterViewPager, adapter)
        }
    }

}