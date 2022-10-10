package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.SearchRepository
import retrofit2.Response

class SearchRepositoryImp(private val apiService: TravelApiService) : SearchRepository {

    /**
     * @return [Response]
     */
    override suspend fun getNearbyList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory(category = "nearby")
    }
    /**
     * @return [Response]
     */
    override suspend fun getTopDestinationList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory(category = "topdestination")
    }
    /**
     * @return [Response]
     */
    override suspend fun updateData(id: String, isBookMark: Boolean): Response<TravelModel> {
        return apiService.updateData(id, isBookMark)
    }
}