package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.ApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.SearchResultRepository
import retrofit2.Response

class SearchResultRepositoryImp(private val apiService: ApiService) : SearchResultRepository {

    override suspend fun getSearchResult(): Response<List<TravelModel>> {
        return apiService.getAllData()
    }
}

