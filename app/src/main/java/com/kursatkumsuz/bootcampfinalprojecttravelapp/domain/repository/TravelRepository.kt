package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import retrofit2.Response

interface TravelRepository {

    suspend fun getAllList() : Response<List<TravelModel>>

    suspend fun getByCategory(category : String) : Response<List<TravelModel>>

    suspend fun updateData(id : String, isBookMark: Boolean): Response<TravelModel>


}