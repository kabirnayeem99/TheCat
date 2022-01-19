package io.github.kabirnayeem99.thecat.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.kabirnayeem99.thecat.data.apiService.CatApiService
import io.github.kabirnayeem99.thecat.data.dataSource.CatRemoteDataSource
import io.github.kabirnayeem99.thecat.data.repository.CatPagingSource
import io.github.kabirnayeem99.thecat.data.repository.CatRepositoryImpl
import io.github.kabirnayeem99.thecat.domain.repository.CatRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun provideCatRepository(dataSource: CatRemoteDataSource): CatRepository {
        return CatRepositoryImpl(dataSource)
    }

    @Provides
    fun provideCatPagingSource(apiService: CatApiService): CatPagingSource {
        return CatPagingSource(apiService)
    }
}