package com.alireza.brochure.feature_brochure.brochure.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alireza.brochure.feature_brochure.brochure.state.BrochureUiState
import com.alireza.brochure.core.dispatcher.IoDispatcher
import com.alireza.brochure.domain.model.brochure.Brochure
import com.alireza.brochure.domain.useCase.GetBrochureListUseCase
import com.alireza.brochure.domain.useCase.FilterBrochureUseCase
import com.alireza.brochureApp.common.model.baseResult.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrochureListViewModel @Inject constructor(
    private val getBrochureListUseCase: GetBrochureListUseCase,
    private val filterBrochureUseCase: FilterBrochureUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _isFilterActive = MutableStateFlow(false)
    val isFilterActive= _isFilterActive.asStateFlow()

    private val _uiState = MutableStateFlow<BrochureUiState>(BrochureUiState.Loading)
    val uiState = _uiState
        .onStart { fetchBrochure() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            BrochureUiState.Loading
        )



    fun fetchBrochure(){
        viewModelScope.launch(ioDispatcher) {
            _isFilterActive.emit(false)
            when (val result = getBrochureListUseCase.invoke()) {
                is BaseResult.Failure -> _uiState.emit(BrochureUiState.Error(result.error))
                is BaseResult.Success -> handleSuccessUiState(result)
            }
        }
    }

    private suspend fun handleSuccessUiState(result: BaseResult.Success<List<Brochure>>) {
        if (result.data.isEmpty()) {
            _uiState.emit(BrochureUiState.EmptyState)
        } else {
            _uiState.emit(BrochureUiState.Success(result.data, result.fromCache))
        }
    }

    fun toggleFilter() {
        viewModelScope.launch {
            _isFilterActive.update { !it }
            when (val result = filterBrochureUseCase.invoke(isFilterActive.value)) {
                is BaseResult.Failure -> _uiState.emit(BrochureUiState.Error(result.error))
                is BaseResult.Success -> handleSuccessUiState(result)
            }
        }
    }
}