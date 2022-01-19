package io.github.kabirnayeem99.thecat.ui.catList

import androidx.paging.PagingData
import io.github.kabirnayeem99.thecat.domain.entity.Cat

data class CatListUiState(
    val isLoading: Boolean = false,
    val message: String = "",
    val catPagingDat: PagingData<Cat> = PagingData.empty()
)
