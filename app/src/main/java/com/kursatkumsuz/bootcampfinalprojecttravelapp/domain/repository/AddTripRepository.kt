package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity

interface AddTripRepository {

    suspend fun insertTrip(trip: TripEntity)
}