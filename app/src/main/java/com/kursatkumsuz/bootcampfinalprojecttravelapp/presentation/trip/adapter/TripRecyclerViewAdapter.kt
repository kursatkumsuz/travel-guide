package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.trip.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemTripBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.tripDiffUtil
import javax.inject.Inject

class TripRecyclerViewAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<TripRecyclerViewAdapter.TripViewHolder>() {

    class TripViewHolder(val binding: ListItemTripBinding) : RecyclerView.ViewHolder(binding.root)

    private var listDiffer = AsyncListDiffer(this, tripDiffUtil)

    var dataList: List<TripEntity>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val binding = ListItemTripBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        //Animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bounce)
        holder.itemView.startAnimation(animation)

        holder.binding.apply {
            holder.binding.tripTitleText.text = dataList[position].title
            holder.binding.tripItemText.text = "1 Image"
            holder.binding.tripHistoryText.text = "${dataList[position].startDate} - ${dataList[position].endDate}"
            holder.binding.tripLeftTimeText.text = "${dataList[position].totalDay} Days"
            glide.load(dataList[position].imageUrl).into(tripImageView)
        }
    }

    override fun getItemCount(): Int {
       return dataList.size
    }
}