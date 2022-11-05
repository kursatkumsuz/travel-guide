package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.imagedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemImageDetailBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.downloadUrl

class ViewPagerAdapter (private val imageList : List<String>) : RecyclerView.Adapter<ViewPagerAdapter.ImagesViewHolder>() {

    class ImagesViewHolder(val binding: ListItemImageDetailBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ListItemImageDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.binding.imageDetailImageView.downloadUrl(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}