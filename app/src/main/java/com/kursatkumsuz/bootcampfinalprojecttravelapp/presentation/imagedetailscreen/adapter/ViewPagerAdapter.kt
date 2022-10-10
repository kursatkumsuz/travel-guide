package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.imagedetailscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemImageDetailBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.stringDiffUtil
import javax.inject.Inject

class ViewPagerAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ViewPagerAdapter.ImagesViewHolder>() {

    class ImagesViewHolder(val binding: ListItemImageDetailBinding) : RecyclerView.ViewHolder(binding.root)

    private val listDiffer = AsyncListDiffer(this, stringDiffUtil)

    var imageList: List<String>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ListItemImageDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.binding.apply {
            glide.load(imageList[position]).into(imageDetailImageView)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}