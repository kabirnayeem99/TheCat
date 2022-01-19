package io.github.kabirnayeem99.thecat.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kabirnayeem99.thecat.data.apiService.CatApiService
import io.github.kabirnayeem99.thecat.data.dataSource.CatRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideCatRemoteDataSource(apiService: CatApiService): CatRemoteDataSource {
        return CatRemoteDataSource(apiService)
    }
}