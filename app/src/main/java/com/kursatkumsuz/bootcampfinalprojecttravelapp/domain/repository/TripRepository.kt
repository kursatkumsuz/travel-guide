package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity

interface TripRepository {

    suspend fun insertTrip(trip: TripEntity)

    suspend fun getTripList() : List<TripEntity>

    suspend fun deleteTrip(trip : TripEntity)
}