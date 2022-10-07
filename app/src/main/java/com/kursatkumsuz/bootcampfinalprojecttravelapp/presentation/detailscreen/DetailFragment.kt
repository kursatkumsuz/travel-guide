package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.detailscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.FragmentDetailBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailFragment @Inject constructor(private val glide: RequestManager) : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var selectedData: Array<TravelModel>
    private val viewModel by viewModels<DetailViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            selectedData = DetailFragmentArgs.fromBundle(it).data
            println("Detail Data $selectedData")
        }

        setVariables()
        addBookmark()
    }

    private fun setVariables() {
        binding.apply {
            val title = selectedData[0].title
            val location = "${selectedData[0].city} , ${selectedData[0].country}"
            val description = selectedData[0].description

            setVariable(BR.title, title)
            setVariable(BR.location, location)
            setVariable(BR.description, description)
            glide.load(selectedData[0].images?.get(0)?.url).into(detailImageView)
        }
    }

    private fun addBookmark() {

        binding.detailAddBookMarkButton.setOnClickListener {
            viewModel.updateData(selectedData[0].id, true)
            println("ID : ${selectedData[0].id}")
        }
    }

}