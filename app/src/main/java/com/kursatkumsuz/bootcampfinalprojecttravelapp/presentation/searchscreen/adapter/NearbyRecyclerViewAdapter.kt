package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemNearbyAttractionsBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.SearchFragmentDirections
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.diffUtil
import javax.inject.Inject

class NearbyRecyclerViewAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<NearbyRecyclerViewAdapter.NearbyViewHolder>() {

    class NearbyViewHolder(val binding: ListItemNearbyAttractionsBinding) : RecyclerView.ViewHolder(binding.root)

    private val listDiffer = AsyncListDiffer(this, diffUtil)
    var dataList: List<TravelModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbyViewHolder {
        val binding = ListItemNearbyAttractionsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NearbyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NearbyViewHolder, position: Int) {

        //Animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fromleft)
        holder.itemView.startAnimation(animation)

        holder.binding.apply {
            glide.load(dataList[position].images?.get(0)?.url).into(nearbyImageView)
            nearbyTitleText.text = dataList[position].title
            nearbyTypeText.text = dataList[position].category
            nearbySubText.text = "${dataList[position].city} , ${dataList[position].country}"
        }

        holder.itemView.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchToDetail(arrayOf(dataList[position]))
            Navigation.findNavController(it).navigate(action)
        }
    }


    override fun getItemCount(): Int {
        return dataList.take(10).size
    }
}