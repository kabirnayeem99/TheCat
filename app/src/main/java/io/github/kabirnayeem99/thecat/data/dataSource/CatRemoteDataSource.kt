package io.github.kabirnayeem99.thecat.data.dataSource

import io.github.kabirnayeem99.thecat.common.Resource
import io.github.kabirnayeem99.thecat.data.apiService.CatApiService
import io.github.kabirnayeem99.thecat.data.dto.mapper.toCatList
import io.github.kabirnayeem99.thecat.domain.entity.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.await
import javax.inject.Inject

class CatRemoteDataSource @Inject constructor(private val apiService: CatApiService) {
    fun getCats(limit: Int, page: Int): Flow<Resource<List<Cat>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val catList = apiService.getCatBreeds(limit, page).await().toCatList()
                emit(Resource.Success(catList))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Could not get the cats"))
            }
        }.flowOn(Dispatchers.IO)
    }
}