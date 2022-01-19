package io.github.kabirnayeem99.thecat.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kabirnayeem99.thecat.BuildConfig
import io.github.kabirnayeem99.thecat.common.CAT_SERVER_HOST
import io.github.kabirnayeem99.thecat.data.apiService.CatApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(CAT_SERVER_HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Singleton
    @Provides
    fun provideCatApiService(retrofit: Retrofit): CatApiService {
        return retrofit.create(CatApiService::class.java)
    }

}