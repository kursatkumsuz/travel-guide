package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.ImageApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.ImageModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.ImageRepository
import retrofit2.Response

class ImageRepositoryImp(private val apiService: ImageApiService) : ImageRepository {

    /**
     * @return [Response]
     */
    override suspend fun searchImage(searchString: String): Response<ImageModel> {
        return apiService.searchImage(searchString = searchString)
    }
}