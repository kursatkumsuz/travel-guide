package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.GuideCategoryModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.GuideRepository
import retrofit2.Response

class GuideRepositoryImp(private val apiService: TravelApiService) : GuideRepository {

    /**
     * @return [Response]
     */
    override suspend fun getMightNeedList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory(category = "mightneed")
    }

    /**
     * @return [Response]
     */
    override suspend fun getTopPickList(): Response<List<TravelModel>> {
        return apiService.getDataByCategory(category = "toppick")
    }

    /**
     * @return [Response]
     */
    override suspend fun updateData(id: String, isBookMark: Boolean): Response<TravelModel> {
        return apiService.updateData(id, isBookMark)
    }
    /**
     * @return [Response]
     */
    override suspend fun getCategories(): Response<List<GuideCategoryModel>> {
        return apiService.getGuideCategories()
    }
}