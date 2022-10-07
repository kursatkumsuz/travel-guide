package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.ApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.HomeRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import retrofit2.Response

class HomeRepositoryImp(private val apiService: ApiService) : HomeRepository {

    override suspend fun getAllList(): Response<List<TravelModel>> {
        return apiService.getAllData()
    }

    override suspend fun getFlightList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory("flight")

    }

    override suspend fun getHotelList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory("hotel")
    }

    override suspend fun getTransportationList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory("transportation")
    }
}