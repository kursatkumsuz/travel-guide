package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemGuideCategoryBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.GuideCategoryModel
import javax.inject.Inject

class GuideCategoryRecyclerViewAdapter @Inject constructor(
    private val glide : RequestManager
):
    RecyclerView.Adapter<GuideCategoryRecyclerViewAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(val binding: ListItemGuideCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    var dataList = ArrayList<GuideCategoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ListItemGuideCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.apply {
            categoryTitleText.text = dataList[position].name
            glide.load(dataList[position].url).into(categoryIconImageView)
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, dataList[position].name, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}