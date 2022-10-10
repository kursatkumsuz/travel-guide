package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.ImageModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {

    /**
     * Gets data from api based on searched word
     * Queries api key and searched word
     * @return [Response]
     */
    @GET("/api/")
    suspend fun searchImage(
        @Query("q") searchString : String,
        @Query("key") apiKey: String = Constants.PIXABAY_API_KEY
    ) : Response<ImageModel>
}