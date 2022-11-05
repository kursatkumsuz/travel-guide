package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guide.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemGuideTopBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guide.GuideFragmentDirections
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.diffUtil
import javax.inject.Inject

class MightNeedAdapter @Inject constructor(
    private val glide: RequestManager
) :
    RecyclerView.Adapter<MightNeedAdapter.MightNeedHolder>() {

    class MightNeedHolder(val binding: ListItemGuideTopBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listDiffer = AsyncListDiffer(this, diffUtil)

    var dataList: List<TravelModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MightNeedHolder {
        val binding =
            ListItemGuideTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MightNeedHolder(binding)
    }

    override fun onBindViewHolder(holder: MightNeedHolder, position: Int) {

        //Animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bounce)
        holder.itemView.startAnimation(animation)
        // set text and load Images Into ImageView
        holder.binding.apply {
            guideTopTitleText.text = dataList[position].title
            glide.load(dataList[position].images?.get(0)?.url).into(guideTopImageView)
        }
        // Navigate -> DetailFragment
        holder.itemView.setOnClickListener {
            val action = GuideFragmentDirections.actionGuideToDetail(arrayOf(dataList[position]))
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dataList.take(10).size
    }
}