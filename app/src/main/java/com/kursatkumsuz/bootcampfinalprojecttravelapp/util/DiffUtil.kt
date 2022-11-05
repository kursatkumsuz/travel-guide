package com.kursatkumsuz.bootcampfinalprojecttravelapp.util

import androidx.recyclerview.widget.DiffUtil
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelImage
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity

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

        val stringDiffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }

        val tripDiffUtil = object : DiffUtil.ItemCallback<TripEntity>() {
            override fun areItemsTheSame(oldItem: TripEntity, newItem: TripEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TripEntity, newItem: TripEntity): Boolean {
                return oldItem == newItem
            }
        }

    }
}