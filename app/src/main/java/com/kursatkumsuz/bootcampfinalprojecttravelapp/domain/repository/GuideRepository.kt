package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import retrofit2.Response

interface GuideRepository {

    suspend fun getMightNeedList() : Response<List<TravelModel>>

    suspend fun getTopPickList() : Response<List<TravelModel>>


}