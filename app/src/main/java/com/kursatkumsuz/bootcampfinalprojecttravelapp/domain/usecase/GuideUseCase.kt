package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.GuideRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import javax.inject.Inject

class GuideUseCase @Inject constructor(
    private val guideRepository: GuideRepository
) {
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
}