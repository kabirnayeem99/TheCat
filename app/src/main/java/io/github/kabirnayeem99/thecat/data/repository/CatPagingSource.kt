package io.github.kabirnayeem99.thecat.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.kabirnayeem99.thecat.data.apiService.CatApiService
import io.github.kabirnayeem99.thecat.data.dto.mapper.toCatList
import io.github.kabirnayeem99.thecat.domain.entity.Cat
import retrofit2.await
import javax.inject.Inject

private const val TAG = "CatPagingSource"

class CatPagingSource @Inject constructor(private val service: CatApiService) :
    PagingSource<Int, Cat>() {
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val pageNumber = params.key ?: 1
        return try {
            val catList = service.getCatBreeds(params.loadSize, pageNumber).await().toCatList()
            Log.d(TAG, "load: catList -> $catList")
            LoadResult.Page(
                data = catList,
                prevKey = pageNumber - 1,
                nextKey = pageNumber + 1
            )
        } catch (e: Exception) {
            Log.e(TAG, "load: ${e.localizedMessage}", e)
            LoadResult.Error(e)
        }
    }
}