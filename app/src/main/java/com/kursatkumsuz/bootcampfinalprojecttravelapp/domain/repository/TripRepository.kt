package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import retrofit2.Response

interface TripRepository {

    suspend fun getBookMarkList(): Response<List<TravelModel>>

    suspend fun updateData(id : String, isBookMark: Boolean): Response<TravelModel>

    suspend fun getTripList() : List<TripEntity>

    suspend fun deleteTrip(trip : TripEntity)


}