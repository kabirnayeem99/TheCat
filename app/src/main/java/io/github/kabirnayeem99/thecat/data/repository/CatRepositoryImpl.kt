package io.github.kabirnayeem99.thecat.data.repository

import io.github.kabirnayeem99.thecat.common.Resource
import io.github.kabirnayeem99.thecat.domain.entity.Cat
import io.github.kabirnayeem99.thecat.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor() :
    CatRepository {
    override fun getCats(limit: Int, page: Int): Flow<Resource<List<Cat>>> {
        return flow { }
    }
}
// 01884202408