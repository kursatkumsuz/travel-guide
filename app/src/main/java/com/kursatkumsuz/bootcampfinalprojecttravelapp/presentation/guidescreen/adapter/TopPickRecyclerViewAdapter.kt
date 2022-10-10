package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemGuideTopPickBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.GuideFragmentDirections
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.diffUtil
import javax.inject.Inject

class TopPickRecyclerViewAdapter @Inject constructor(
    private val glide: RequestManager
) :
    RecyclerView.Adapter<TopPickRecyclerViewAdapter.TopPickHolder>() {

    class TopPickHolder(val binding: ListItemGuideTopPickBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener: ((String) -> Unit)? = null


    private var listDiffer = AsyncListDiffer(this, diffUtil)

    var dataList: List<TravelModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    var itemPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPickHolder {
        val binding = ListItemGuideTopPickBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopPickHolder(binding)
    }

    override fun onBindViewHolder(holder: TopPickHolder, position: Int) {

        // set animations
        val bottomAnimation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.frombottom)
        val bounceAnimation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bounce)
        holder.itemView.startAnimation(bottomAnimation)
        // set texts and load Images Into ImageView
        holder.binding.apply {
            guideTopPickTitleText.text = dataList[position].title
            guideTopPickTypeText.text = dataList[position].country
            glide.load(dataList[position].images?.get(0)?.url).into(guideTopPickmageView)
        }

        holder.itemView.setOnClickListener {
            val action = GuideFragmentDirections.actionGuideToDetail(arrayOf(dataList[position]))
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.apply {
            guideTopPickBookmarkButton.setOnClickListener {
                // start animation
                guideTopPickBookmarkButton.startAnimation(bounceAnimation)
                // set a new value itemPosition
                itemPosition = position
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