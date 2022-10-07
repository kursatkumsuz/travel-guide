package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemGuideBottomBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.GuideFragmentDirections
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.diffUtil
import javax.inject.Inject

class TopPickRecyclerViewAdapter @Inject constructor(
    private val glide: RequestManager
) :
    RecyclerView.Adapter<TopPickRecyclerViewAdapter.TopPickHolder>() {

    class TopPickHolder(val binding: ListItemGuideBottomBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listDiffer = AsyncListDiffer(this, diffUtil)

    var dataList: List<TravelModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPickHolder {
        val binding = ListItemGuideBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopPickHolder(binding)
    }

    override fun onBindViewHolder(holder: TopPickHolder, position: Int) {

        //Animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fromleft)
        holder.itemView.startAnimation(animation)
        // set texts and load Images Into ImageView
        holder.binding.apply {
            guideBottomTitleText.text = dataList[position].title
            guideBottomTypeText.text = dataList[position].country
            glide.load(dataList[position].images?.get(0)?.url).into(guideBottomImageView)
        }

        holder.itemView.setOnClickListener {
            val action = GuideFragmentDirections.actionGuideToDetail(arrayOf(dataList[position]))
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dataList.take(10).size
    }
}