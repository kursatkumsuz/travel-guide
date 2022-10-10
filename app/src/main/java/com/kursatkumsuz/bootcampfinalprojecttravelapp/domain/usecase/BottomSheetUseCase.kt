package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.BottomSheetRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import javax.inject.Inject

class BottomSheetUseCase @Inject constructor(
    private val bottomSheetRepository : BottomSheetRepository
) {

    /**
     * Gets all list from api
     * Checks whether response is successful or not
     * @return [Resource]
     */
    suspend fun getAllList() : Resource<List<TravelModel>> {

        return try {
            val response = bottomSheetRepository.getAllList()

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("No Data", null)
            } else {
                Resource.error("Server is busy! Try later!" , null)
            }
        } catch (e: Exception) {
            Resource.error("Try again later!", null)
        }
    }

    /**
     * Updates value of isBookmark that given id
     * Checks whether response is successful or not
     */
    suspend fun updateData(id: String, isBookmark: Boolean) : Resource<TravelModel>  {

        return try {
            val response = bottomSheetRepository.updateData(id, isBookmark)

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