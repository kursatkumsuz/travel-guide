package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.detailscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemDetailImagesBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelImage
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.imageDiffUtil
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.stringDiffUtil
import javax.inject.Inject

class DetailImagesRecyclerViewAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<DetailImagesRecyclerViewAdapter.ImagesHolder>() {

    class ImagesHolder(val binding: ListItemDetailImagesBinding) : RecyclerView.ViewHolder(binding.root)

    // Initialize AsyncListDiffer to compute the difference between two lists
    private val listDiffer = AsyncListDiffer(this, imageDiffUtil)

    var imageList: List<TravelImage>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesHolder {
        val binding = ListItemDetailImagesBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ImagesHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesHolder, position: Int) {
        // Load Images
        glide.load(imageList[position].url).into(holder.binding.detailListImageView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}