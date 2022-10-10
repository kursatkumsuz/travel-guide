package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.addbookmarkscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.R
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemAddBookmarkBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil
import javax.inject.Inject

class AddBookmarkAdapter @Inject constructor(
    private val glide: RequestManager
) :
    RecyclerView.Adapter<AddBookmarkAdapter.BottomSheetHolder>() {

    class BottomSheetHolder(val binding: ListItemAddBookmarkBinding) : RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener: ((String) -> Unit)? = null

    // Initialize AsyncListDiffer to compute the difference between two lists
    private val listDiffer = AsyncListDiffer(this, DiffUtil.diffUtil)

    var dataList: List<TravelModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetHolder {
        val binding = ListItemAddBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BottomSheetHolder(binding)
    }

    override fun onBindViewHolder(holder: BottomSheetHolder, position: Int) {
        // Initialize and start animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.bounce)
        holder.itemView.startAnimation(animation)

        // Set text and load Images
        holder.binding.apply {
            glide.load(dataList[position].images?.get(0)?.url).into(bottomSheetImageView)
            bottomSheetTitleText.text = dataList[position].title

        }
        // Pass Id of clicked Item to onItemClickListener
        holder.binding.bottomSheetFab.setOnClickListener {
            onItemClickListener?.let {
                it(dataList[position].id)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}