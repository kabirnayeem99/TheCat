package io.github.kabirnayeem99.thecat.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.kabirnayeem99.thecat.data.dataSource.CatRemoteDataSource
import io.github.kabirnayeem99.thecat.domain.entity.Cat
import javax.inject.Inject

private const val TAG = "CatPagingSource"

class CatPagingSourceRepository @Inject constructor(private val dataSource: CatRemoteDataSource) :
    PagingSource<Int, Cat>() {
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val pageNumber = params.key ?: 1
        return try {
            val catList = dataSource.getCats(params.loadSize, pageNumber)
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