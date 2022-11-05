package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.TravelRepository
import retrofit2.Response

class TravelRepositoryImp(private val apiService: TravelApiService) : TravelRepository {

    override suspend fun getAllList(): Response<List<TravelModel>> {
        return apiService.getAllData()
    }

    override suspend fun getByCategory(category: String): Response<List<TravelModel>> {
        return apiService.getDataByCategory(category)
    }

    override suspend fun updateData(id: String, isBookMark: Boolean): Response<TravelModel> {
        return apiService.updateData(id, isBookMark)
    }

}