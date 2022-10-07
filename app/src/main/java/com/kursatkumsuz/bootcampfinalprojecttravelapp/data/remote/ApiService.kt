package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/allData")
    suspend fun getAllData(): Response<List<TravelModel>>

    @GET("/allData")
    suspend fun getDataByCategory(
        @Query("category") category: String
    ): Response<List<TravelModel>>

    @GET("/allData")
    suspend fun getBookMarkData(
        @Query("isBookmark") isBookMark: Boolean = true
    ): Response<List<TravelModel>>



    @PUT("/allData/{id}")
    @FormUrlEncoded
   suspend fun updateData(
        @Path("id") id : String,
        @Field("isBookmark") isBookmark : Boolean
    ): Response<TravelModel>

}