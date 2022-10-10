package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.ImageApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.TravelApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.ImageModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.SearchImageRepository
import retrofit2.Response

class SearchImageRepositoryImp(private val apiService: ImageApiService) : SearchImageRepository {

    /**
     * @return [Response]
     */
    override suspend fun searchImage(searchString: String): Response<ImageModel> {
        return apiService.searchImage(searchString = searchString)
    }
}