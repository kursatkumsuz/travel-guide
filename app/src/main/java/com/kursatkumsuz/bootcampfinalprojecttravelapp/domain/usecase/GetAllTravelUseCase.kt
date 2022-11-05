package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.TravelRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import javax.inject.Inject

class GetAllTravelUseCase @Inject constructor(private val travelRepository: TravelRepository) {

    suspend fun getAllTravel(): Resource<List<TravelModel>> {
        val response = travelRepository.getAllList()
        return try {
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
}