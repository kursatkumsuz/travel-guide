package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.TripRepository
import javax.inject.Inject

class DeleteTripUseCase @Inject constructor(
    private val tripRepository: TripRepository
){

    /**
     * Deletes selected data from room database
     * @param [trip] for [deleteTrip]
     */
    suspend fun deleteTrip(trip: TripEntity) {
        tripRepository.deleteTrip(trip)
    }
}