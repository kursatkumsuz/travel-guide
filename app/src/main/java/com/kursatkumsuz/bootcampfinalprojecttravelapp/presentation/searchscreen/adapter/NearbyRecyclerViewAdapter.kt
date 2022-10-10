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

    class NearbyViewHolder(val binding: ListItemNearbyAttractionsBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener: ((String) -> Unit)? = null

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

        //set and start animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bounce)
        holder.itemView.startAnimation(animation)
        // set texts and Image
        holder.binding.apply {
            glide.load(dataList[position].images?.get(0)?.url).into(nearbyImageView)
            nearbyTitleText.text = dataList[position].title
            nearbyTypeText.text = dataList[position].category
            nearbySubText.text = "${dataList[position].city} , ${dataList[position].country}"
        }
        // navigate -> DetailFragment
        holder.itemView.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchToDetail(arrayOf(dataList[position]))
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.apply {
            nearbyBookmarkButton.setOnClickListener {
                // start animation
                nearbyBookmarkButton.startAnimation(animation)
                //pass clicked Item id to SearchFragment
                onItemClickListener?.let {
                    it(dataList[position].id)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return dataList.take(10).size
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}