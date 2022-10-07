package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.BookmarkRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Response
import javax.inject.Inject

class TripUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend fun getBookMarkList(): Resource<List<TravelModel>> {
        return try {
            val response = bookmarkRepository.getBookMarkList()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("No Data", null)
            } else {
                Resource.error("No Data", null)
            }
        } catch (e: Exception) {
            Resource.error("No Data", null)
        }
    }

    suspend fun updateData(id: String, isBookmark: Boolean): Response {
        return try {
            val response = bookmarkRepository.updateData(id, isBookmark)
            if (response.isSuccessful) {
                println("Response is success!")
                return Response.success("Deleted")
            } else {
                Response.error("Server is busy! Try later!")
            }
        } catch (e : Exception) {
            Response.error("Try again later!")
        }
    }
}