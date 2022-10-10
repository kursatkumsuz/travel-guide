package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.GuideCategoryModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.GuideRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import javax.inject.Inject

class GuideUseCase @Inject constructor(
    private val guideRepository: GuideRepository
) {

    /**
     * Gets category might need data
     * Checks whether response is successful or not
     * @return [Resource]
     */
    suspend fun getMightNeedList(): Resource<List<TravelModel>> {
        return try {
            val response = guideRepository.getMightNeedList()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("No Data", null)
            } else {
                Resource.error("No Data", null)
            }

        } catch (e: Exception) {
            Resource.error("Error", null)
        }
    }

    /**
     * Gets category top pick data
     * Checks whether response is successful or not
     * @return [Resource]
     */
    suspend fun getTopPickList(): Resource<List<TravelModel>> {
        return try {
            val response = guideRepository.getTopPickList()
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.success(it)
                } ?: Resource.error("No Data", null)
            } else {
                Resource.error("No Data", null)
            }
        } catch (e: Exception) {
            Resource.error("Error", null)
        }
    }

    /**
     * Updates value of isBookmark that given id
     * Checks whether response is successful or not
     * @param [id] for [updateData]
     * @param [isBookmark] for [updateData]
     */
    suspend fun updateData(id: String, isBookmark: Boolean) {

        val response = guideRepository.updateData(id, isBookmark)
        try {
            if (response.isSuccessful) {
                println(response.message())
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }

    /**
     * Gets category data
     * @return [List]
     */
    suspend fun getCategories() : List<GuideCategoryModel> {
        return guideRepository.getCategories()
    }

}