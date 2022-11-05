package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.ImageModel
import retrofit2.Response

interface ImageRepository {

    suspend fun searchImage(searchString : String) : Response<ImageModel>
}