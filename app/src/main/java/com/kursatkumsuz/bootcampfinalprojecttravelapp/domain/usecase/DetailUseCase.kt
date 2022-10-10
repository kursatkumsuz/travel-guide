package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.DetailRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {

    /**
     * Updates value of isBookmark that given id
     * Checks whether response is successful or not
     * @param [id] for [updateData]
     * @param [isBookmark] for [updateData]
     */
    suspend fun updateData(id: String, isBookmark: Boolean): Resource<TravelModel> {

        return try {
            val response = detailRepository.updateData(id, isBookmark)

            if (response.isSuccessful) {
                println("Response is success!")
                return Resource.success(null)
            } else {
                Resource.error("Server is busy! Try later!", null)
            }
        } catch (e: Exception) {
            Resource.error("Try again later!", null)
        }
    }
}