package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.ImageModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Constants.Companion.PIXABAY_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {

    @GET("/api/")
    suspend fun searchImage(
        @Query("q") searchString: String,
        @Query("key") apiKey: String = PIXABAY_API_KEY
    ): Response<ImageModel>

}
