package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import retrofit2.Response

interface SearchRepository {

    suspend fun getNearbyList() : Response<List<TravelModel>>

    suspend fun getTopDestinationList() : Response<List<TravelModel>>

    suspend fun updateData(id : String, isBookMark: Boolean): Response<TravelModel>


}