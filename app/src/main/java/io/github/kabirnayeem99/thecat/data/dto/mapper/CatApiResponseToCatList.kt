package io.github.kabirnayeem99.thecat.data.dto.mapper

import android.util.Log
import io.github.kabirnayeem99.thecat.data.dto.cat.CatApiResponseDto
import io.github.kabirnayeem99.thecat.domain.entity.Cat

private const val TAG = "CatApiResponseToCatList"
fun CatApiResponseDto.toCatList(): List<Cat> {
    return try {
        val catList = map {
            Cat(it.id, it.url)
        }
        catList
    } catch (e: Exception) {
        Log.e(TAG, "toCatList: ${e.localizedMessage}", e)
        emptyList()
    }
}