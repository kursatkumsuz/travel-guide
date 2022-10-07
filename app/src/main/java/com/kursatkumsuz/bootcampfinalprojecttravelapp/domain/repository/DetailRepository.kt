package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import retrofit2.Call
import retrofit2.Response

interface DetailRepository {

    suspend fun updateData(id : String, isBookMark: Boolean): Response<TravelModel>
}