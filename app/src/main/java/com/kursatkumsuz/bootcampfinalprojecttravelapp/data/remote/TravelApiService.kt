package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import retrofit2.Response
import retrofit2.http.*

interface TravelApiService {

    /**
     * Gets all data from api
     * @return [Response]
     */
    @GET("/allData")
    suspend fun getAllData(): Response<List<TravelModel>>

    /**
     * Gets data from api by categories
     * Queries given category word
     * @return [Response]
     */
    @GET("/allData")
    suspend fun getDataByCategory(
        @Query("category") category: String
    ): Response<List<TravelModel>>

    /**
     * Updates value of isBookmark that given id
     * @return [Response]
     */
    @PUT("/allData/{id}")
    @FormUrlEncoded
    suspend fun updateData(
        @Path("id") id: String,
        @Field("isBookmark") isBookmark: Boolean
    ): Response<TravelModel>

}