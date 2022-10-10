package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.HomeRepository
import retrofit2.Response

class HomeRepositoryImp(private val apiService: TravelApiService) : HomeRepository {

    /**
     * @return [Response]
     */
    override suspend fun getAllList(): Response<List<TravelModel>> {
        return apiService.getAllData()
    }

    /**
     * @return [Response]
     */
    override suspend fun getFlightList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory(category = "flight")
    }

    /**
     * @return [Response]
     */
    override suspend fun getHotelList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory(category = "hotel")
    }

    /**
     * @return [Response]
     */
    override suspend fun getTransportationList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory(category = "transportation")
    }
}