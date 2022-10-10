package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.searchimagescreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemSearchImageBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.DiffUtil.Companion.stringDiffUtil
import javax.inject.Inject

class SearchImageRecyclerViewAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<SearchImageRecyclerViewAdapter.SearchImageHolder>() {

    class SearchImageHolder(val binding: ListItemSearchImageBinding) : RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener: ((String) -> Unit)? = null

    private val listDiffer = AsyncListDiffer(this, stringDiffUtil)

    var images: List<String>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageHolder {
        val binding =
            ListItemSearchImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchImageHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchImageHolder, position: Int) {
        glide.load(images[position]).into(holder.binding.searchImageView)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(images[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}