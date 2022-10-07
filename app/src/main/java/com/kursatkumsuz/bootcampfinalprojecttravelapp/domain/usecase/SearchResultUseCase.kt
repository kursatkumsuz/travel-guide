package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import androidx.lifecycle.LiveData
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.TravelModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.SearchResultRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import java.util.*
import javax.inject.Inject

class SearchResultUseCase @Inject constructor(
    private val repository: SearchResultRepository
) {

    suspend fun getSearchResult(): Resource<List<TravelModel>> {
        return try {
            val response = repository.getSearchResult()

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)

                } ?: Resource.error("No Data", null)
            } else {
                Resource.error("No Data", null)
            }
        } catch (e: Exception) {
            Resource.error("Error", null)
        }
    }

}