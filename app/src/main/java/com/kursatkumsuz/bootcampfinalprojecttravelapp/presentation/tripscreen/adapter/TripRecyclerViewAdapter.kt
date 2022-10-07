package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.adapter

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

class TripRecyclerViewAdapter @Inject constructor(
    private var glide: RequestManager
) : RecyclerView.Adapter<TripRecyclerViewAdapter.TripHolder>() {

    class TripHolder(val binding: ListItemTripBinding) : RecyclerView.ViewHolder(binding.root)


    private var listDiffer = AsyncListDiffer(this, diffUtil)

    var dataList: List<TravelModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripHolder {
        val binding = ListItemTripBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TripHolder(binding)
    }

    override fun onBindViewHolder(holder: TripHolder, position: Int) {

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