package com.kursatkumsuz.bootcampfinalprojecttravelapp.presentation.guidescreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kursatkumsuz.bootcampfinalprojecttravelapp.databinding.ListItemGuideCategoryBinding
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.GuideCategoryModel

class GuideCategoryRecyclerViewAdapter(private val categoryList: ArrayList<GuideCategoryModel>) :
    RecyclerView.Adapter<GuideCategoryRecyclerViewAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(val binding: ListItemGuideCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ListItemGuideCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.apply {
            categoryTitleText.text = categoryList[position].title
            categoryIconImageView.setImageResource(categoryList[position].icon)
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, categoryList[position].title, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}