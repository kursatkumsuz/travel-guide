package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import retrofit2.Response

interface HomeRepository {

    suspend fun getAllList() : Response<List<TravelModel>>

    suspend fun getFlightList() : Response<List<TravelModel>>

    suspend fun getHotelList() : Response<List<TravelModel>>

    suspend fun getTransportationList() : Response<List<TravelModel>>

}