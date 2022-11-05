package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.local.TripDao
import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.TripRepository

class TripRepositoryImp(
    private val apiService: TravelApiService,
    private val dao: TripDao
) : TripRepository {


    /**
     * Runs function of deleteTrip that in the TripDao
     * @param [trip] for insert into database
     */
    override suspend fun insertTrip(trip: TripEntity) {
        dao.insertTrip(trip)
    }

    /**
     * @return [List]
     */
    override suspend fun getTripList(): List<TripEntity> {
        return dao.getTrips()
    }

    /**
     * Runs function of deleteTrip that in the TripDao
     * @param [trip] for delete from room database
     */
    override suspend fun deleteTrip(trip: TripEntity) {
        dao.deleteTrip(trip)
    }
}