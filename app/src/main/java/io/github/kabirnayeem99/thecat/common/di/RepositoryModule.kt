package io.github.kabirnayeem99.thecat.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.kabirnayeem99.thecat.data.dataSource.CatRemoteDataSource
import io.github.kabirnayeem99.thecat.data.repository.CatPagingSourceRepository
import io.github.kabirnayeem99.thecat.data.repository.CatRepositoryImpl
import io.github.kabirnayeem99.thecat.domain.repository.CatRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun provideCatRepository(dataSource: CatRemoteDataSource): CatRepository {
        return CatRepositoryImpl()
    }

    @Provides
    fun provideCatPagingSource(dataSource: CatRemoteDataSource): CatPagingSourceRepository {
        return CatPagingSourceRepository(dataSource)
    }
}