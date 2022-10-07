package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.DetailRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {

    suspend fun updateData(id: String, isBookmark: Boolean) {

        val response = detailRepository.updateData(id, isBookmark)
         try {
            if (response.isSuccessful) {
                println(response.message())
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }
}