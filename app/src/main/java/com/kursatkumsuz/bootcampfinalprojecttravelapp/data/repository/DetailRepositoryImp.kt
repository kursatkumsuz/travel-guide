package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.DetailRepository
import retrofit2.Response

class DetailRepositoryImp(val apiService: TravelApiService) : DetailRepository {

    /**
     * @return [Response]
     */
    override suspend fun updateData(id: String, isBookMark: Boolean): Response<TravelModel> {
        return apiService.updateData(id = id, isBookmark = isBookMark)
    }
}