package io.github.kabirnayeem99.thecat.data.apiService

import io.github.kabirnayeem99.thecat.data.dto.cat.CatApiResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("images/search")
    fun getCatBreeds(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): Call<CatApiResponseDto>
}