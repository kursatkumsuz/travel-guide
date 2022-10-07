package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.ApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.GuideRepository
import retrofit2.Response

class GuideRepositoryImp(private val apiService: ApiService) : GuideRepository {
    override suspend fun getMightNeedList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory("mightneed")
    }

    override suspend fun getTopPickList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory("toppick")
    }
}