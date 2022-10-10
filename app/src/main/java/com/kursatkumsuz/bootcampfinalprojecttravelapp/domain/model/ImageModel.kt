package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model

data class ImageModel(
    val total: Int,
    val totalHits: Int,
    val hits: List<ImageResult>
)
