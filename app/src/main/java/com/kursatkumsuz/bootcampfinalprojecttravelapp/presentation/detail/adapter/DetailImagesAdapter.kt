package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemDetailImagesBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelImage
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.downloadUrl

class DetailImagesAdapter (private val imageList : List<TravelImage>): RecyclerView.Adapter<DetailImagesAdapter.ImagesHolder>() {

    class ImagesHolder(val binding: ListItemDetailImagesBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesHolder {
        val binding = ListItemDetailImagesBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ImagesHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesHolder, position: Int) {
        // Load Images
        holder.binding.detailListImageView.downloadUrl(imageList[position].url)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}