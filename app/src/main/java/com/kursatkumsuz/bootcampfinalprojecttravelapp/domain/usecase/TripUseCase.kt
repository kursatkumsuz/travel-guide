package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TripEntity
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.TripRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import javax.inject.Inject

class TripUseCase @Inject constructor(
    private val bookmarkRepository: TripRepository
) {

    /**
     * Gets all data
     * Checks whether response is successful or not
     * @return [Resource]
     */
    suspend fun getBookMarkList(): Resource<List<TravelModel>> {
        return try {
            val response = bookmarkRepository.getBookMarkList()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("No Data", null)
            } else {
                Resource.error("No Data", null)
            }
        } catch (e: Exception) {
            Resource.error("No Data", null)
        }
    }

    /**
     * Updates value of isBookmark that given id
     * Checks whether response is successful or not
     * @param [id] for [updateData]
     * @param [isBookmark] for [updateData]
     */
    suspend fun updateData(id: String, isBookmark: Boolean): Resource<TravelModel> {
        return try {
            val response = bookmarkRepository.updateData(id, isBookmark)

            if (response.isSuccessful) {
                println("Response is success!")
                return Resource.success(null)
            } else {
                Resource.error("Server is busy! Try later!", null)
            }
        } catch (e : Exception) {
            Resource.error("Try again later!", null)
        }
    }

    /**
     * Gets data from room database
     * @return [List]
     */
    suspend fun getTripList() : List<TripEntity> {
        return bookmarkRepository.getTripList()
    }
    /**
     * Deletes selected data from room database
     * @param [trip] for [deleteTrip]
     */
    suspend fun deleteTrip(trip: TripEntity) {
        bookmarkRepository.deleteTrip(trip)
    }
}