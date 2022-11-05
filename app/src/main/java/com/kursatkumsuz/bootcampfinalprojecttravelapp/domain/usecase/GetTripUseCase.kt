package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.TripRepository
import javax.inject.Inject

class GetTripUseCase @Inject constructor(
    private val bookmarkRepository: TripRepository
) {


    /**
     * Gets data from room database
     * @return [List]
     */
    suspend fun getTripList() : List<TripEntity> {
        return bookmarkRepository.getTripList()
    }

}