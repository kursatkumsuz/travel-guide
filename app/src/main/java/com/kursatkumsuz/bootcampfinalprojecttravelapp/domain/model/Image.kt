package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val altText: String? = null,
    val height: Int,
    val isHeroImage: Boolean,
    val url: String,
    val width: Int
) : Parcelable