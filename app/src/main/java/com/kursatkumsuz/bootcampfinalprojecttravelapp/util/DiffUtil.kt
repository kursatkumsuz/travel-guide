package com.kursatkumsuz.bootcampfinalprojecttravelapp.util

import androidx.recyclerview.widget.DiffUtil
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel

class DiffUtil {

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<TravelModel>() {
            override fun areItemsTheSame(oldItem: TravelModel, newItem: TravelModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TravelModel, newItem: TravelModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}