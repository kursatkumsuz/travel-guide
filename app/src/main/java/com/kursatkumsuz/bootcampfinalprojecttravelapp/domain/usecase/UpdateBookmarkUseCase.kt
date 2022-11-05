package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.TravelRepository
import javax.inject.Inject

class UpdateBookmarkUseCase @Inject constructor(
    private val travelRepository: TravelRepository
) {

    /**
     * Updates value of isBookmark that given id
     * Checks whether response is successful or not
     * @param [id] for [updateData]
     * @param [isBookmark] for [updateData]
     */
    suspend fun updateBookmark(id: String, isBookmark: Boolean) {
        val response = travelRepository.updateData(id, isBookmark)
        try {
            if (response.isSuccessful) {
                println(response.message())
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }
}