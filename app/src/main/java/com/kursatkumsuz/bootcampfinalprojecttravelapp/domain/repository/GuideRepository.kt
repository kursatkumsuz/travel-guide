package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.GuideCategoryModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import retrofit2.Response

interface GuideRepository {

    suspend fun getMightNeedList() : Response<List<TravelModel>>

    suspend fun getTopPickList() : Response<List<TravelModel>>

    suspend fun updateData(id : String, isBookMark: Boolean): Response<TravelModel>

    suspend fun getCategories(): List<GuideCategoryModel>

}