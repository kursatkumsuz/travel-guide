package com.kursatkumsuz.bootcampfinalprojecttravelapp.data.repository

import com.kursatkumsuz.bootcampfinalprojecttravelapp.data.remote.ApiService
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.BookmarkRepository
import retrofit2.Call
import retrofit2.Response

class BookmarkRepositoryImp(private val apiService: ApiService) : BookmarkRepository {

    override suspend fun getBookMarkList(): Response<List<TravelModel>> {
        return apiService.getBookMarkData()
    }

    override suspend fun updateData(id: String, isBookMark: Boolean): Response<TravelModel> {
        return apiService.updateData(id, isBookMark)
    }
}