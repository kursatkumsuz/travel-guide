package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemHomeBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.home.HomeFragmentDirections
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.downloadUrl

class HomeAdapter (private val dataList : List<TravelModel>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemHomeBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bounce)
        holder.itemView.startAnimation(animation)
        // Load Images into ImageView
        holder.binding.homeListImageView.downloadUrl(dataList[position].images?.get(0)?.url)
        // Navigate -> DetailFragment
        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToDetail(arrayOf(dataList[position]))
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}