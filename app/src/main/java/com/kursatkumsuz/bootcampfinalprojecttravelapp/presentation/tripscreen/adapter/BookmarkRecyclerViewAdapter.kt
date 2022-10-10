package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemTripBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.homescreen.HomeFragmentDirections
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.tripscreen.TripFragmentDirections
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.diffUtil
import javax.inject.Inject

class BookmarkRecyclerViewAdapter @Inject constructor(
    private var glide: RequestManager
) : RecyclerView.Adapter<BookmarkRecyclerViewAdapter.BookmarkHolder>() {

    class BookmarkHolder(val binding: ListItemTripBinding) : RecyclerView.ViewHolder(binding.root)


    private var listDiffer = AsyncListDiffer(this, diffUtil)

    var dataList: List<TravelModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkHolder {
        val binding = ListItemTripBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkHolder, position: Int) {

        // Animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bounce)
        holder.itemView.startAnimation(animation)

        // Load Images and set texts
        holder.binding.apply {
            tripTitleText.text = dataList[position].title
            tripItemText.text = "${dataList[position].images?.size} Images"
            tripLeftTimeText.visibility = View.INVISIBLE
            glide.load(dataList[position].images?.get(0)?.url).into(tripImageView)

            // Navigate -> DetailFragment
            holder.itemView.setOnClickListener {
                val action = TripFragmentDirections.actionTripToDetail(arrayOf(dataList[position]))
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}