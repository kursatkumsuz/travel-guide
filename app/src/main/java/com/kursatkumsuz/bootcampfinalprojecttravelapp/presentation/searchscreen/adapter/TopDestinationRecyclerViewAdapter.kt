package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemTopDestinationBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchscreen.SearchFragmentDirections
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.diffUtil
import javax.inject.Inject

class TopDestinationRecyclerViewAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<TopDestinationRecyclerViewAdapter.TopDestinationHolder>() {

    class TopDestinationHolder(val binding: ListItemTopDestinationBinding) : RecyclerView.ViewHolder(binding.root)

    private val listDiffer = AsyncListDiffer(this, diffUtil)
    var dataList: List<TravelModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDestinationHolder {
        val binding = ListItemTopDestinationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TopDestinationHolder(binding)
    }

    override fun onBindViewHolder(holder: TopDestinationHolder, position: Int) {

        //Animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bounce)
        holder.itemView.startAnimation(animation)

        holder.binding.apply {
            topDestinationTitleText.text = dataList[position].title
            topDestinationSubText.text = dataList[position].country
            glide.load(dataList[position].images?.get(0)?.url).into(topDestinationImageView)
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