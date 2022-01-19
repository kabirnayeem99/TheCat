package io.github.kabirnayeem99.thecat.data.repository

import io.github.kabirnayeem99.thecat.common.Resource
import io.github.kabirnayeem99.thecat.data.dataSource.CatRemoteDataSource
import io.github.kabirnayeem99.thecat.domain.entity.Cat
import io.github.kabirnayeem99.thecat.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(private val dataSource: CatRemoteDataSource) :
    CatRepository {
    override fun getCats(limit: Int, page: Int): Flow<Resource<List<Cat>>> {
        return dataSource.getCats(limit, page)
    }
}