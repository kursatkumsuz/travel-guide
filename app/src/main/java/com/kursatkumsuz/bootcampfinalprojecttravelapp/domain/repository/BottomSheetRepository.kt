package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import retrofit2.Response

interface BottomSheetRepository {

    suspend fun getAllList() : Response<List<TravelModel>>

    suspend fun updateData(id : String, isBookMark: Boolean): Response<TravelModel>


}