package io.github.kabirnayeem99.thecat.data.dataSource

import io.github.kabirnayeem99.thecat.data.apiService.CatApiService
import io.github.kabirnayeem99.thecat.data.dto.mapper.toCatList
import io.github.kabirnayeem99.thecat.domain.entity.Cat
import retrofit2.await
import javax.inject.Inject

class CatRemoteDataSource @Inject constructor(private val apiService: CatApiService) {
    suspend fun getCats(limit: Int, page: Int): List<Cat> {
        return try {
            val catList = apiService.getCatBreeds(limit, page).await().toCatList()
            catList
        } catch (e: Exception) {
            emptyList<Cat>()
        }
    }
}