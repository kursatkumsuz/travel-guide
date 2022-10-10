package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TravelModel(
    val category: String,
    val city: String,
    val country: String,
    val description: String,
    val id: String,
    val images: List<TravelImage>? = null,
    val isBookmark: Boolean,
    val title: String
) : Parcelable