package com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.usecase

import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.model.ImageModel
import com.kursatkumsuz.bootcampfinalprojecttravelapp.domain.repository.SearchImageRepository
import com.kursatkumsuz.bootcampfinalprojecttravelapp.util.Resource
import javax.inject.Inject

class SearchImageUseCase @Inject constructor(
    private val searchImageRepository: SearchImageRepository
) {
    /**
     * Gets Images based on given word
     * Checks whether response is successful or not
     * @return [Resource]
     * @param [searchString] for query
     */
    suspend fun searchImage(searchString: String): Resource<ImageModel> {
        return try {
            val response = searchImageRepository.searchImage(searchString)

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("No data", null)
            } else {
                Resource.error(response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("No data", null)
        }
    }
}