package io.github.kabirnayeem99.thecat.domain.repository

import io.github.kabirnayeem99.thecat.common.Resource
import io.github.kabirnayeem99.thecat.domain.entity.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    fun getCats(limit: Int = 10, page: Int = 1): Flow<Resource<List<Cat>>>
}