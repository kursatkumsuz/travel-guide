package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.GuideCategoryModel
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

    @GET("/categories")
    suspend fun getGuideCategories(): Response<List<GuideCategoryModel>>

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
     * Gets data that value of isBookmark is true from api
     * Queries value of isBookmark
     * @return [Response]
     */
    @GET("/allData")
    suspend fun getBookMarkData(
        @Query("isBookmark") isBookMark: Boolean = true
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