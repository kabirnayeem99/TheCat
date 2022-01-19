package io.github.kabirnayeem99.thecat.ui.catList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kabirnayeem99.thecat.data.repository.CatPagingSource
import io.github.kabirnayeem99.thecat.domain.entity.Cat
import io.github.kabirnayeem99.thecat.domain.repository.CatRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val repo: CatRepository,
    private val pagingSource: CatPagingSource
) : ViewModel() {

    private val _catListUiState = MutableStateFlow(CatListUiState())
    val catListUiState = _catListUiState.asStateFlow()

    val catListFlow: Flow<PagingData<Cat>> =
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = { pagingSource }
        ).flow.cachedIn(viewModelScope)

    private var fetchCatListJob: Job? = null
    fun getCatList() {
        fetchCatListJob?.cancel()
        fetchCatListJob = viewModelScope.launch {
            _catListUiState.update { it.copy(isLoading = true) }
            catListFlow.collect { pagingData ->
                _catListUiState.update {
                    it.copy(
                        isLoading = false,
                        catPagingDat = pagingData
                    )
                }
            }
        }
    }

}