package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.ApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.SearchRepository
import retrofit2.Response

class SearchRepositoryImp(private val apiService: ApiService) : SearchRepository {

    override suspend fun getNearbyList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory("nearby")
    }

    override suspend fun getTopDestinationList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory("topdestination")
    }
}