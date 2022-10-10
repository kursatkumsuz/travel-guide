package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.local.TripDao
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.AddTripRepository

class AddTripRepositoryImp(private val dao: TripDao) : AddTripRepository {

    /**
     * Runs function of insertTrip that in the TripDao
     * @param [trip] for insert into room database
     */
    override suspend fun insertTrip(trip: TripEntity) {
        dao.insertTrip(trip)
    }
}