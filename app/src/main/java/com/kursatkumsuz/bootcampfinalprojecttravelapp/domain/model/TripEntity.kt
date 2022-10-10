package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class TripEntity(
    val title: String,
    val country: String,
    val city: String,
    val startDate : String,
    val endDate : String,
    val totalDay : Int,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)

