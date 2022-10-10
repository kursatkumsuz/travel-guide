package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.GuideCategoryModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.GuideRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.GuideCategoryConstants.Companion.getCategory
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
     * @return [List]
     */
    override suspend fun getCategories(): List<GuideCategoryModel> {
        return getCategory()
    }
}