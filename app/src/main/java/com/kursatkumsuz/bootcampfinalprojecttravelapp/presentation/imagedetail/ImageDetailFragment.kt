package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.imagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kursatkumsuz.bootcampfinalprojecttravelapp.BR
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentImageDetailBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.imagedetail.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageDetailFragment : DialogFragment() {


    private lateinit var binding : FragmentImageDetailBinding
    private var adapter = ViewPagerAdapter(arrayListOf())
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
            adapter = ViewPagerAdapter(images.toList())
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