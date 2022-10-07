package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import retrofit2.Response

interface SearchResultRepository {

    suspend fun getSearchResult() : Response<List<TravelModel>>

}