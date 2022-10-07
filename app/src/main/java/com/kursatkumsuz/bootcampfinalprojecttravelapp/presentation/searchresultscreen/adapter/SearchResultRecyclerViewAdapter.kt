package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchresultscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemTripBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.diffUtil
import javax.inject.Inject

class SearchResultRecyclerViewAdapter @Inject constructor(
    private val glide : RequestManager
):
    RecyclerView.Adapter<SearchResultRecyclerViewAdapter.SearchDetailHolder>() {

    class SearchDetailHolder(val binding: ListItemTripBinding) : RecyclerView.ViewHolder(binding.root)

    private val listDiffer = AsyncListDiffer(this, diffUtil)

    var dataList: List<TravelModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDetailHolder {
        val binding = ListItemTripBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return SearchDetailHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchDetailHolder, position: Int) {

        //Animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bounce)
        holder.itemView.startAnimation(animation)

        holder.binding.apply {
            glide.load(dataList[position].images?.get(0)?.url).into(tripImageView)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}