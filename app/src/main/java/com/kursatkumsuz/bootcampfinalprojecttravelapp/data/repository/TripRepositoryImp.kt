package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.local.TripDao
import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.TripRepository
import retrofit2.Response

class TripRepositoryImp(
    private val apiService: TravelApiService,
    private val dao: TripDao
) : TripRepository {

    /**
     * @return [Response]
     */
    override suspend fun getBookMarkList(): Response<List<TravelModel>> {
        return apiService.getBookMarkData()
    }

    /**
     * @return [Response]
     */
    override suspend fun updateData(id: String, isBookMark: Boolean): Response<TravelModel> {
        return apiService.updateData(id = id, isBookmark = isBookMark)
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