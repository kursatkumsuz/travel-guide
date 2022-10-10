package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.BottomSheetRepository
import retrofit2.Response

class BottomSheetRepositoryImp (private val apiService: TravelApiService) : BottomSheetRepository {

    /**
     * @return [Response]
     */
    override suspend fun getAllList(): Response<List<TravelModel>> {
        return apiService.getAllData()
    }


    /**
     * @return [Response]
     */
    override suspend fun updateData(id: String, isBookMark: Boolean): Response<TravelModel> {
        return apiService.updateData(id = id, isBookmark = isBookMark)
    }
}