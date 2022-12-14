package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.TripRepository
import javax.inject.Inject

class AddTripUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    /**
     * Saves data Into Room database
     */
    suspend fun createTrip(
        title: String,
        country: String,
        city: String,
        startDate: String,
        endDate: String,
        totalDay: Int,
        image: String
    ) {
        val trip = TripEntity(title, country, city, startDate, endDate, totalDay, image)
        tripRepository.insertTrip(trip)
    }
}