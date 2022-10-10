package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.SearchRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    /**
     * Gets category top data
     * Checks whether response is successful or not
     * @return [Resource]
     */
    suspend fun getTopList(): Resource<List<TravelModel>> {
        return try {
            val response = searchRepository.getTopDestinationList()

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("No Data", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No Data", null)
        }
    }

    /**
     * Gets category nearby data
     * Checks whether response is successful or not
     * @return [Resource]
     */
    suspend fun getNearbyList(): Resource<List<TravelModel>> {
        return try {
            val response = searchRepository.getNearbyList()

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("No Data", null)
            } else {
                Resource.error("Error", null)
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
    suspend fun updateData(id: String, isBookmark: Boolean) {

        val response = searchRepository.updateData(id, isBookmark)
        try {
            if (response.isSuccessful) {
                println(response.message())
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }

}